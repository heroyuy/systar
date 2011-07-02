package engine;

import com.soyostar.app.Color;
import com.soyostar.app.Component;
import com.soyostar.app.Container;
import com.soyostar.app.Image;
import com.soyostar.app.KeyEvent;
import com.soyostar.app.Painter;
import com.soyostar.app.TouchEvent;
import com.soyostar.app.widget.WButton;

/**
 *
 * 视图渲染层
 */
public class RenderLayer extends Container implements Component.Callback {

    private GameEngine ge = null;
    private Image buffImg;//双缓冲
    private Painter painter;

    public Painter getPainter() {
        return painter;
    }

    protected RenderLayer(GameEngine ge) {
        super();
        this.ge = ge;
        WButton wb = new WButton(Image.createImage("res/image/battler/001-Fighter01.png"), Image.createImage("res/image/battler/002-Fighter02.png"));
        wb.setText("你好");
        wb.setVisible(true);
        wb.setSize(200, 60);
        wb.setBackground(Color.RED);
        this.addLayer(wb);
    }

    @Override
    public void paintSelf(Painter p) {
        p.drawImage(buffImg, 0, 0, Painter.LT);

    }

    @Override
    public void onTouchEventSelf(TouchEvent te) {
        ge.setTouchEvent(te);
    }

    @Override
    public void onKeyEvent(KeyEvent ke) {
        ge.setKeyEvent(ke);
    }

    public void sizeChanged(int width, int height) {
        buffImg = Image.createImage(getWidth(), getHeight());
        painter = buffImg.getPainter();
    }
}
