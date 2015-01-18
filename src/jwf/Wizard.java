
package jwf;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.Frame;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.VerticalLayout;

/**
 * This class controls a wizard.<p>
 *  Add it to a frame or any other container then call start with your initial
 *  wizard panel.<p>
 * Listeners can also be added to trap when the wizard finishes and when
 *  the wizard is cancelled.
 *
 * @author Christopher Brind
 */
public class Wizard extends JXPanel implements ActionListener {
    public static final String BACK_I18N = "BACK_I18N";
    public static final String NEXT_I18N = "NEXT_I18N";
    public static final String FINISH_I18N = "FINISH_I18N";
    public static final String CANCEL_I18N = "CANCEL_I18N";
    public static final String HELP_I18N = "HELP_I18N";
    public static final Dimension WIZARD_WINDOW_SIZE = new Dimension( 450, 200 );

    private final JXButton backButton = new JXButton("Back");
    private final JXButton nextButton = new JXButton("Next");
    //private final JXButton finishButton = new JXButton("inish");
    private final JXButton cancelButton = new JXButton("Cancel");
    //private final JXButton helpButton = new JXButton("help");

    private final HashMap listeners = new HashMap();

    private Stack previous = null;
    private WizardPanel current = null;
    private WizardContext ctx = null;
    private Map i18n = null;
    private JXPanel westPanel;

    public Wizard( Map i18n ) {
        this.i18n = i18n;
        init();
        this.applyI18N( this.i18n );
    }
    /** Creates a new wizard. */
    public Wizard() {
        init();
    }

    private void init() {
        nextButton.setText("Next");
        nextButton.addActionListener(this);
        backButton.addActionListener(this);
        //finishButton.addActionListener(this);
        cancelButton.addActionListener(this);
        //helpButton.addActionListener(this);

        nextButton.setEnabled(false);
        backButton.setEnabled(false);
        //finishButton.setEnabled(false);
        cancelButton.setEnabled(false);
        //helpButton.setEnabled(false);
        
        setLayout(new BorderLayout());

        JXPanel navButtons = new JXPanel();
        navButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        navButtons.add(cancelButton);

        navButtons.add(backButton);
        navButtons.add(nextButton);
        //navButtons.add(finishButton);

        //JXPanel cancelButtons = new JXPanel();
        //cancelButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
       // cancelButtons.add(cancelButton);
        //cancelButtons.add(helpButton);

        JXPanel buttons = new JXPanel();
        buttons.setLayout(new BorderLayout());
        buttons.add(navButtons, BorderLayout.EAST);
        //buttons.add(cancelButtons, BorderLayout.WEST);

        add(buttons, BorderLayout.SOUTH);
        createWestPanel();
        
        add(westPanel,BorderLayout.WEST);
        setMinimumSize( WIZARD_WINDOW_SIZE );
        setPreferredSize( WIZARD_WINDOW_SIZE );
    }
    
    
    public void createWestPanel(){
    	westPanel = new JXPanel();
    	ArrayList<Component> step=new ArrayList();
    			JRadioButton welcome=new JRadioButton("Welcome");
    	    	JRadioButton bundle=new JRadioButton("Select bundle");
    	    	JRadioButton serial=new JRadioButton("Serial Number");
    	    	JRadioButton instal=new JRadioButton("Install set");
    	    	JRadioButton client=new JRadioButton("Cleient Setting");
    	    	JRadioButton directory=new JRadioButton("Directory");
    	    	JRadioButton security=new JRadioButton("Security");

    	    	JRadioButton database=new JRadioButton("Database");
    	    	JRadioButton mysql=new JRadioButton("MySQL");
    	    	JRadioButton summary=new JRadioButton("Summary");
    	    	JRadioButton filecoping=new JRadioButton("Fil Coppyng");
    step.add(welcome);
    step.add(bundle);
    step.add(serial);
    step.add(instal);
    step.add(client);
    step.add(directory);
    step.add(security);
    step.add(database);
    step.add(mysql);
    step.add(summary);
    step.add(filecoping);
    addStepInstalltion(step);
    	

    	//westPanel.add(comp)
    }
    
    public void addStepInstalltion(ArrayList<Component> steps){
    	//VerticalLayout vertical =new VerticalLayout();
    	//VerticalLayout vertical = new VerticalLayout ();
    	GroupLayout InstallBarLayout = new javax.swing.GroupLayout(westPanel);
    	westPanel.setLayout(InstallBarLayout);
    	Group group;
    	//SequeancialGroup squencial=InstallBarLayout.createSequentialGroup();
    	for(Component comp:steps){
    		comp.setEnabled(false);
    		comp.setFocusable(false);
    		//group.addComponent(comp);
    		//vertical.addLayoutComponent(comp.getName(), comp);

    	}
    	//InstallBarLayout.setVerticalGroup(group);
    	
    	 /*InstallBarLayout.setVerticalGroup(
    	            InstallBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    	            .addGroup(InstallBarLayout.createSequentialGroup()*/
    	//westPanel.add(vertical);

    }

    /* Sets a map of labels for changing the labels of the wizard buttons
     * The keys are the I18N constants of the Wizard class
     * @param map A Map object containing 5 key-value elements
     */
    public void setI18NMap( Map map ) {
        i18n = map;
        applyI18N( i18n );
    }

    private void applyI18N( Map map ) {
        if ( map.size() > 0 ) {
            nextButton.setText( (String)map.get( NEXT_I18N ) );
            backButton.setText( (String)map.get( BACK_I18N ) );
           // finishButton.setText( (String)map.get( FINISH_I18N ) );
            cancelButton.setText( (String)map.get( CANCEL_I18N ) );
            //helpButton.setText( (String)map.get( HELP_I18N ) );

            backButton.setActionCommand( "<< back" );
            nextButton.setActionCommand( "next >>" );
            //finishButton.setActionCommand( "finish" );
            cancelButton.setActionCommand( "cancel" );
            //helpButton.setActionCommand( "help" );

        }
    }

    /** Add a listener to this wizard.
     * @param listener a WizardListener object
     */
    public void addWizardListener(WizardListener listener) {
        listeners.put(listener, listener);
    }

    /** Remove a listener from this wizard.
     * @param listener a WizardListener object
     */
    public void removeWizardListener(WizardListener listener) {
        listeners.remove(listener);
    }

    /** Start this wizard with this panel. */
    public void start(WizardPanel wp) {
        previous = new Stack();
        ctx = new WizardContext();
        wp.setWizardContext(ctx);
        setPanel(wp);
        updateButtons();
    }

    /** Handle's button presses.
     * param ae an ActionEvent object
     */
    public void actionPerformed(ActionEvent ae) {

        String ac = ae.getActionCommand();
        if ("Back".equals(ac)) {
            back();
        } else if ("Next".equals(ac)) {
            next();
       // } else if ("finish".equals(ac)) {
         //   finish();
        } else if ("Cancel".equals(ac)) {
            cancel();
        //} else if ("help".equals(ac)) {
           // help();
        }

    }

    private void setPanel(WizardPanel wp) {
        if (null != current) {
            remove(current);
        }

        current = wp;
        if (null == current) {
            current = new NullWizardPanel();
        }
        add(current, BorderLayout.CENTER);

        Iterator iter = listeners.values().iterator();
        while(iter.hasNext()) {
            WizardListener listener = (WizardListener)iter.next();
            listener.wizardPanelChanged(this);
        }
        setVisible(true);
        revalidate();
        updateUI();
        current.display();
    }

    private void updateButtons() {
        cancelButton.setEnabled(true);
        //helpButton.setEnabled(current.hasHelp());
        backButton.setEnabled(previous.size() > 0);
        nextButton.setEnabled(current.hasNext());
        //finishButton.setEnabled(current.canFinish());
    }

    private void back() {

        WizardPanel wp = (WizardPanel)previous.pop();
        setPanel(wp);
        updateButtons();

    }

    private void next() {
        ArrayList list = new ArrayList();
        if (current.validateNext(list)) {
            previous.push(current);
            WizardPanel wp = current.next();
            if (null != wp) {
                wp.setWizardContext(ctx);
            }

            setPanel(wp);
            updateButtons();
        } else {
            showErrorMessages(list);
        }
    }

    private void finish() {

        ArrayList list = new ArrayList();
        if (current.validateFinish(list)) {
            current.finish();
            Iterator iter = listeners.values().iterator();
            while(iter.hasNext()) {
                WizardListener listener = (WizardListener)iter.next();
                listener.wizardFinished(this);
            }
        } else {
            showErrorMessages(list);
        }
    }

    private void cancel() {

        Iterator iter = listeners.values().iterator();
        while(iter.hasNext()) {
            WizardListener listener = (WizardListener)iter.next();
            listener.wizardCancelled(this);
        }
    }

    private void help() {
        current.help();
    }

    private void showErrorMessages(ArrayList list) {
        Window w = SwingUtilities.windowForComponent(this);
        JFrame frame = null;
        ErrorMessageBox errorMsgBox = null;

        if (w instanceof Frame) {
            errorMsgBox = new ErrorMessageBox((Frame)w);
        } else if (w instanceof Dialog) {
            errorMsgBox = new ErrorMessageBox((Dialog)w);
        } else {
            errorMsgBox = new ErrorMessageBox();
        }

        errorMsgBox.showErrorMessages(list);
    }

}

