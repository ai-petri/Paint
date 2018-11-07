
import org.eclipse.swt.*;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.*;

public class MainClass 
{

	static boolean isDown = false;
	static int X;
	static int Y;
	
	static int red = 0;
	static int green = 0;
	static int blue = 0;
	
	public static void main(String[] args)
	{
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Paint");
		shell.setLayout(new GridLayout());
		
		RowLayout layout = new RowLayout();
		layout.spacing = 20;
		
		
		
		Composite row = new Composite(shell, 0);
		row.setLayout(layout);
		
		Label t1 = new Label(row,0);
		t1.setText("Red:");
		t1.pack();
		Spinner r = new Spinner(row, 0);
		r.setMaximum(255);
		r.pack();
		Label t2 = new Label(row,0);
		t2.setText("Green:");		
		t2.pack();
		Spinner g = new Spinner(row, 0);
		g.setMaximum(255);
		g.pack();
		Label t3 = new Label(row,0);
		t3.setText("Blue:");		
		t3.pack();
		Spinner b = new Spinner(row, 0);
		b.setMaximum(255);
		b.pack();
		
		row.pack();
		
		
		Canvas canvas = new Canvas (shell, SWT.BORDER);
		GC gc = new GC(canvas);
		
		
		
		
		r.addSelectionListener(new SelectionAdapter() {
			@Override
            public void widgetSelected(SelectionEvent e)
            {
				red = r.getSelection();
				gc.setForeground(new Color(null,red,green,blue));
            }
		});
		
		g.addSelectionListener(new SelectionAdapter() {
			@Override
            public void widgetSelected(SelectionEvent e)
            {
				green = g.getSelection();
				gc.setForeground(new Color(null,red,green,blue));
            }
		});
		
		b.addSelectionListener(new SelectionAdapter() {
			@Override
            public void widgetSelected(SelectionEvent e)
            {
				blue = b.getSelection();
				gc.setForeground(new Color(null,red,green,blue));
            }
		});
			
		
		
		canvas.setBackground(display.getSystemColor (SWT.COLOR_WHITE));
		canvas.setCursor(new Cursor(display, SWT.CURSOR_CROSS));
		
		
		canvas.setLayoutData(new GridData(shell.getClientArea().width - 10, shell.getClientArea().height - 10));
		canvas.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent event) {
				canvas.setLayoutData(new GridData(shell.getClientArea().width - 10, shell.getClientArea().height - 10));
				
			}
		});
		
		
		canvas.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				isDown = false;
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
			
			gc.drawRectangle(e.x, e.y, 1, 1);
			isDown = true;
			X = e.x;
			Y = e.y;
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				
				
			}
			
		});
		
		canvas.addMouseMoveListener(new MouseMoveListener() {
			
			@Override
			public void mouseMove(MouseEvent e) {
			
			if (isDown == true){
				gc.drawLine(X, Y, e.x, e.y);
				gc.drawLine(X+1, Y, e.x+1, e.y);
				gc.drawLine(X+2, Y, e.x+2, e.y);
				X = e.x;
				Y = e.y;
			}
				
			}
		});
		
	
		
		
		shell.open();
		
		
		
		while(!shell.isDisposed())
		{
			if(!display.readAndDispatch())
			display.sleep ();			
		}
		display.dispose();
		
	}
	
}
