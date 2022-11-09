package visitor;

import user.User;
import user.UserGroup;

public interface NodeVisitor {
    
    public void visit(User user);
    public void visit(UserGroup userGroup);

}
