package org.opentrafficsim.editor.extensions;

import java.rmi.RemoteException;
import java.util.function.Consumer;

import org.djutils.event.Event;
import org.djutils.event.EventListener;
import org.opentrafficsim.editor.OtsEditor;
import org.opentrafficsim.editor.XsdTreeNode;
import org.opentrafficsim.editor.XsdTreeNodeRoot;

/**
 * Allows the user to save definitions separately.
 * @author wjschakel
 */
public class DefinitionsSaver implements EventListener, Consumer<XsdTreeNode>
{
    /** */
    private static final long serialVersionUID = 20230914L;

    /** Editor. */
    private OtsEditor editor;

    /**
     * Constructor.
     * @param editor OtsEditor; editor.
     * @throws RemoteException if listener cannot be added.
     */
    public DefinitionsSaver(final OtsEditor editor) throws RemoteException
    {
        editor.addListener(this, OtsEditor.NEW_FILE);
        this.editor = editor;
    }

    /** {@inheritDoc} */
    @Override
    public void notify(final Event event) throws RemoteException
    {
        if (event.getType().equals(OtsEditor.NEW_FILE))
        {
            XsdTreeNodeRoot root = (XsdTreeNodeRoot) event.getContent();
            root.addListener(this, XsdTreeNodeRoot.NODE_CREATED);
        }
        else if (event.getType().equals(XsdTreeNodeRoot.NODE_CREATED))
        {
            XsdTreeNode node = (XsdTreeNode) event.getContent();
            if (!node.isType("Ots.Definitions.xi:include") && node.getParent() != null
                    && node.getParent().isType("Ots.Definitions"))
            {
                node.addConsumer("Save as include file...", this);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void accept(final XsdTreeNode node)
    {
        this.editor.saveFileAs(node);
    }
}
