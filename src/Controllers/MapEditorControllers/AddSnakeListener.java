package Controllers.MapEditorControllers;

import Core.Game.GameCreatorWrapper;
import Views.MapEditorView.Frame;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ISmir on 06.11.2016.
 */
public class AddSnakeListener extends MapEditorAbstractController implements ActionListener
{
    public AddSnakeListener(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        super(gameCreator, mapEditorFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        throw new NotImplementedException();
//        gameCreator.placeSnake(1);
    }
}
