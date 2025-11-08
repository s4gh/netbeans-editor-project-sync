package io.github.s4gh.projecteditorsyncactions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.Actions;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.Presenter;

@ActionID(
    category = "Editor",
    id = "io.github.s4gh.navigator.SelectInFavoritesToolbarAction"
)
@ActionRegistration(
    displayName = "#CTL_SelectInFavoritesToolbarAction",
    lazy = false
)
@ActionReference(
    path = "Editors/Toolbars/Default",
    position = 1515
)
@Messages("CTL_SelectInFavoritesToolbarAction=Select in Favorites")
public final class SelectInFavoritesToolbarAction extends AbstractAction implements Presenter.Toolbar {

    private static final String CATEGORY = "Window/SelectDocumentNode";
    private static final String BUILT_IN_ID = "org.netbeans.modules.favorites.Select"; // Ctrl+Shift+3

    public SelectInFavoritesToolbarAction() {
        putValue(NAME, Bundle.CTL_SelectInFavoritesToolbarAction());
        Action builtIn = Actions.forID(CATEGORY, BUILT_IN_ID);
        if (builtIn != null) {
            Object tip = builtIn.getValue(Action.SHORT_DESCRIPTION);
            if (tip instanceof String) {
                putValue(SHORT_DESCRIPTION, tip);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Action builtIn = Actions.forID(CATEGORY, BUILT_IN_ID);
        if (builtIn != null && builtIn.isEnabled()) {
            builtIn.actionPerformed(e);
        }
    }

    @Override
    public Component getToolbarPresenter() {
        Action builtIn = Actions.forID(CATEGORY, BUILT_IN_ID);
        JButton btn = new JButton();
        if (builtIn != null) {
            // Inherit enablement, icon, tooltip, etc. from the built-in action
            Actions.connect(btn, builtIn);
        } else {
            // Fallback (e.g., if Favorites module is disabled)
            btn.setText(Bundle.CTL_SelectInFavoritesToolbarAction());
            Object tip = getValue(SHORT_DESCRIPTION);
            if (tip instanceof String) {
                btn.setToolTipText((String) tip);
            }
            btn.addActionListener(this);
        }
        return btn;
    }
}