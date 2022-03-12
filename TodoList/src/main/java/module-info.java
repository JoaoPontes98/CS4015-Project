module com.todo.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.todo.todolist to javafx.fxml;
    exports com.todo.todolist;
}