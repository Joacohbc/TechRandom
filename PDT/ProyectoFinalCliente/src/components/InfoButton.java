package components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class InfoButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	private Icon messageIcon;
	
	public InfoButton() {
		setIcon(new ImageIcon(VTextBox.class.getResource("/components/info16.png")));
		setBorder(null);
		messageIcon = new ImageIcon(VTextBox.class.getResource("/components/info32.png"));
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMessage();
			}
		});
	}

	private void showMessage() {
		JOptionPane.showMessageDialog(null, getToolTipText(), "Información", JOptionPane.INFORMATION_MESSAGE, messageIcon);
	}
	
	@Override
	public void setText(String text) {
		super.setToolTipText(text);
	}
	
	@Override
	public void setIcon(Icon defaultIcon) {
		super.setIcon(defaultIcon);
	}

	public Icon getMessageIcon() {
		return messageIcon;
	}

	public void setMessageIcon(Icon messageIcon) {
		this.messageIcon = messageIcon;
	}
}
