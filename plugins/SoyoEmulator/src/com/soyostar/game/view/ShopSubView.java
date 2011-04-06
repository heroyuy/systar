package com.soyostar.game.view;

import emulator.Canvas;
import emulator.EmulatorGraphics;
import engine.BaseView;
import engine.GameEngine;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.Bag;
import model.GameData;
import system.Painter;

/**
 *
 * @author Administrator
 */
public class ShopSubView extends Canvas {

    private GameData gd = GameData.getGameData();
    private GameEngine ge = GameEngine.getInstance();
    private JTextField jTextField_Num = null;
    private JLabel jLabel = null;
    private JButton confirm = null;
    private JButton cancel = null;

    public ShopSubView() {
        super();
        setLayout(null);
        jTextField_Num = new JTextField();
        jTextField_Num.setSize(120, 20);
        jTextField_Num.setLocation(20, 50);
        add(jTextField_Num);

        jLabel = new JLabel("请输入数量");
        jLabel.setSize(120, 20);
        jLabel.setLocation(20, 20);
        add(jLabel);

        confirm = new JButton("确定");
        confirm.setSize(60, 20);
        confirm.setLocation(20, gd.screenHeight - 20 - 20);
        confirm.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    gd.shop_itemNum = Integer.parseInt(jTextField_Num.getText());
                } catch (Exception ee) {
                    gd.shop_itemNum = 1;
                }
                if (gd.shop_tabIndex == 0) {
                    buy();
                } else {
                    sell();
                }
                gd.shop_pageIndex = ShopView.PAGE_TIP;
                ge.switchToRenderLayer();
            }
        });
        add(confirm);
        cancel = new JButton("取消");
        cancel.setSize(60, 20);
        cancel.setLocation(gd.screenWidth - 60 - 20, gd.screenHeight - 20 - 20);
        cancel.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent e) {

                ge.switchToRenderLayer();
            }
        });
        add(cancel);
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
