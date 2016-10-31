package Views.Styles;

import java.awt.*;

/**
 * Created by Владимир on 30.10.2016.
 */
public final class MenuStyle
{
    // TODO: code review by Van

    // TODO: Фактически, то что здесь написано не подразумевает никакой расширяемости.
    // TODO: Это так себе, учитывая, что мне вроде не очень нравится стиль.

    // TODO: Ещё очень плохо, что всё статическое.
    // TODO: Захотим поменять - придётся в точках использования всё заменять, либо просто здесь менять код.

    // TODO: Есть 2 выхода:
    // TODO: 1. Сделать систему с интерфейсом и его реализациями, как это сделано с GameStyle
    // TODO: 2. Сделать класс MenuStyle более конфигурируемым. Например, принимать в конструктор Font, TextColor, ... И добавить методы типа setFont
    // TODO: Так можно будет не реализовывать для каждого стиля какой-то класс, а просто писать немножко кода в создании игры.
    // TODO: С другой стороны, паттерн с реализацией интерфейса позволяет легко переключаться между разными стилями.
    public static Font getFont(int size)
    {
        return new Font("Tahoma", Font.BOLD, size);
    }
    public static final Color textColor = Color.YELLOW;
    public static final Color pressedButtonColor = new Color(255, 215, 0);
    public static final Color backgroundColor = Color.GREEN;
}
