package visitor;

import user.User;
import user.UserGroup;

public class AnalysisVisitor implements NodeVisitor {

    @Override
    public void visit(User user) {
        user.incrementUserTotal();
    }

    @Override
    public void visit(UserGroup userGroup) {
        userGroup.incrementGroupTotal();
    }

}
