package viewcontroller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by bak on 12/08/2015.
 */
interface Press extends MouseListener {
    @Override
    default void mouseEntered(MouseEvent e) {}
    @Override
    default void mouseExited(MouseEvent e) {}
    @Override
    default void mouseClicked(MouseEvent e) {}
    @Override
    default void mouseReleased(MouseEvent e) {}
}