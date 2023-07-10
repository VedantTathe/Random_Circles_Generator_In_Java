import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class MyFrame extends JFrame implements ActionListener, Runnable
{
	private JLabel lbl;
	private JButton b1, b2;
	private JProgressBar jpb;
	private Graphics gr;
	private JPanel p1, p2;
	private Canvas cnv;
	private Thread t;


	public MyFrame()
	{
		p1 = new JPanel();
		p1.setLayout(new GridLayout(1,2));

		b1 = new JButton("START");
		p1.add(b1);
		b1.addActionListener(this);

		b2 = new JButton("PAUSE");
		p1.add(b2);
		b2.addActionListener(this);
		b2.setEnabled(false);

		this.add(p1, BorderLayout.NORTH);

		cnv = new Canvas();
		cnv.setBackground(Color.RED);
		this.add(cnv, BorderLayout.EAST);


		p2 = new JPanel();
		p2.setLayout(new GridLayout(2,1));

		lbl = new JLabel("0%", JLabel.CENTER);
		p2.add(lbl);

		jpb = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
		p2.add(jpb);

		this.add(p2, BorderLayout.SOUTH);


		this.setVisible(true);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String text = e.getActionCommand();

			
		switch(text)
		{
			case "START": 
				b2.setEnabled(true);
				t = new Thread(this);
				t.start();
				break;
			case "PAUSE": 
				t.suspend();
				b2.setText("RESUME");
				break;
			case "RESUME": 
				t.resume();
				b2.setText("PAUSE");
				break; 
		}
	}

	@Override
	public void run()
	{
		try
		{

			gr = getGraphics();
			b1.setEnabled(false);
		
			for(int i = 0; i<=100;i++)
			{
				Random obj = new Random();
				float r = obj.nextFloat();
				float g = obj.nextFloat();
				float b = obj.nextFloat();

				Color clr = new Color(r, g, b);
				gr.setColor(clr);

				int x = obj.nextInt(450+1-50)+50;
				int y = obj.nextInt(400+1-100)+100;
				int w = obj.nextInt(50);

				gr.fillOval(x, y, w, w);	


				// double value = jpb.getPercentComplete()*100;
				int value = jpb.getValue();
				lbl.setText(value+"%");
				jpb.setValue(i);
				Thread.sleep(200);
			}

			b1.setEnabled(true);
			b2.setEnabled(false);

		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, ex);
		}
	}

}





class Sample
{
	public static void main(String[] args) {
		new MyFrame();
	}
}