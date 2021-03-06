package ro.redeul.google.go.inspection.fix;

import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import ro.redeul.google.go.GoEditorAwareTestCase;
import ro.redeul.google.go.lang.psi.GoFile;
import ro.redeul.google.go.lang.psi.expressions.literals.GoLiteralIdentifier;

import static ro.redeul.google.go.lang.psi.utils.GoPsiUtils.findParentOfType;

public class CreateGlobalVariableFixTest extends GoEditorAwareTestCase {
    public void testSimple() throws Exception{ doTest(); }
    public void testUnderImport() throws Exception{ doTest(); }
    public void testAppendVariable() throws Exception{ doTest(); }
    public void testAppendToMultipleVariable() throws Exception{ doTest(); }

    @Override
    protected void invoke(final Project project, final Editor editor, final GoFile file) {
        PsiElement element = file.findElementAt(editor.getSelectionModel().getSelectionStart());
        final GoLiteralIdentifier identifier = findParentOfType(element, GoLiteralIdentifier.class);
        assertNotNull(identifier);

        CommandProcessor.getInstance().executeCommand(project, new Runnable() {
            @Override
            public void run() {
                new CreateGlobalVariableFix(identifier).invoke(project, file, editor, identifier, identifier);
            }
        }, "", null);
    }

    @Override
    protected String getTestDataRelativePath() {
        return "fixes/createGlobalVariable/";
    }
}
