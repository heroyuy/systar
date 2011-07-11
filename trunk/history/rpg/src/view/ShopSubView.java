package view;

import engine.GameEngine;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import model.Bag;
import model.GameData;

/**
 *
 * @author Administrator
 */
public class ShopSubView extends Form implements CommandListener {

    private TextField itemNum = new TextField("请输入数量", "", 15, TextField.NUMERIC);//数量
    private Command confirm = new Command("确定", Command.OK, 1);//确定按钮
    private Command cancel = new Command("取消", Command.CANCEL, 2);//取消按钮
    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();

    public ShopSubView() {
        super("");
        addCommand(confirm);
        addCommand(cancel);
        this.append(itemNum);
        setCommandListener(this);

    }

    public void commandAction(Command cmd, Displayable d) {

        if (cmd == confirm) {
            try {
                gd.shop_itemNum = Integer.parseInt(itemNum.getString());
            } catch (Exception e) {
                gd.shop_itemNum = 1;
            }
            if (gd.shop_tabIndex == 0) {
                buy();
            } else {
                sell();
            }
            gd.shop_pageIndex = ShopView.PAGE_TIP;
            ge.switchToRenderLayer();
        } else if (cmd == cancel) {
            ge.switchToRenderLayer();
        }
    }

    private void buy() {
        if (gd.player.money >= gd.shop_items_buy[gd.shop_curIndex].price * gd.shop_itemNum) {
            gd.player.money -= gd.shop_items_buy[gd.shop_curIndex].price * gd.shop_itemNum;
            gd.player.bag.add(gd.shop_type == 0 ? Bag.ITEM : Bag.EQUIP, gd.shop_items_buy[gd.shop_curIndex].index, gd.shop_itemNum);
            gd.shop_message = "购买成功，获得" + gd.shop_items_buy[gd.shop_curIndex].name + " " + gd.shop_itemNum + "个";
            gd.buildList_sell();
        } else {
            gd.shop_message = "金钱不足，购买失败";
        }

    }

    private void sell() {
        if (gd.player.bag.getNum(gd.shop_type == 0 ? Bag.ITEM : Bag.EQUIP, gd.shop_items_sell[gd.shop_curIndex].index) >= gd.shop_itemNum) {
            gd.player.bag.del(gd.shop_type == 0 ? Bag.ITEM : Bag.EQUIP, gd.shop_items_sell[gd.shop_curIndex].index, gd.shop_itemNum);
            gd.player.money += gd.shop_items_sell[gd.shop_curIndex].price / 2 * gd.shop_itemNum;
            gd.shop_message = "卖出成功，获得金钱" + (gd.shop_items_sell[gd.shop_curIndex].price / 2 * gd.shop_itemNum);
            gd.buildList_sell();
            gd.shop_topIndex = gd.shop_curIndex = 0;
            gd.shop_needRebuild = true;
        } else {
            gd.shop_message = gd.shop_items_sell[gd.shop_curIndex].name + "数量不足，卖出失败";
        }
    }
}
