import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

public class Sframe extends JFrame implements ActionListener  {
	private  JLabel searchLabel;
	private  JCheckBox checkbox1;
	private  JCheckBox checkbox2;
	private  JLabel searchParameter;
	private  JTextField userText;
	private  JButton button1;
	
	public Sframe()
	{ 
		JPanel panel = new JPanel();
		setTitle("Graphic Grabber");
	       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setSize(200,200);
	       add(panel);	       
	       panel.setLayout(null);
	       setVisible(true);
	       
	       searchLabel = new JLabel("Search by:");
	       searchLabel.setBounds(1500, 150, 180, 25);
	       panel.add(searchLabel);
	      
	       
	       // Checkboxes
	       checkbox1 = new JCheckBox("Author");
	       checkbox1.setBounds(1550, 200, 80, 25);
	       panel.add(checkbox1);
	       
	       checkbox2 = new JCheckBox("Tags");
	       checkbox2.setBounds(1550, 250, 80, 25);
	       panel.add(checkbox2);
	       checkbox2.setSelected(false);
	       
	       
	 // Parameters
	       searchParameter = new JLabel("Search Parameter");
	       searchParameter.setBounds(1500, 300, 180, 25);
	       panel.add(searchParameter);  
	       
	       userText = new JTextField();
	       userText.setBounds(1500, 320, 180, 25);
	       panel.add(userText);
	   
	   // Button
	       button1 = new JButton("Search");
	       button1.setBounds(1500, 700, 180, 25);
	       button1.addActionListener(this);
	       panel.add(button1);
	   
		
	}
	public static void main(String[] args) {
		 Sframe frame = new Sframe();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String user = userText.getText();
		
			System.out.println("Searched for "+ user );
		}
		

	}
		
	


