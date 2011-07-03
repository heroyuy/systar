package engine;

import com.soyostar.app.Component;
import com.soyostar.app.Container;
import com.soyostar.app.Image;
import com.soyostar.app.KeyEvent;
import com.soyostar.app.Painter;
import com.soyostar.app.TouchEvent;

/**
 *
 * 视图渲染层
 */
class RenderLayer extends Container implements Component.Callback, Render {

    private GameEngine ge = null;
    private Image buffImg;//双缓冲
    private Painter painter;

    Painter getPainter() {
        return painter;
    }

    RenderLayer(GameEngine ge) {
        super();
        this.ge = ge;
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
