package editor;

import java.awt.BasicStroke;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;



import mode.BaseMode;
import object.BaseObject;
import object.BaseShape;
import object.Group;
import object.Line;


import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;



 

public class Canvas extends JLayeredPane {
 
	private static Canvas instance = null; //single instance



	
	private ArrayList<BaseObject> umlObjectList;
	private ArrayList<Line> umlLine;
	private ArrayList<Group> groupList;
	private Boolean isDraging = false, isSelectOnGroup = false, isSelectOnObject = false;
	private int objectID, groupID, perfectRect_x, perfectRect_y, perfectRect_w, perfectRect_h;
	private String situation;
	private BaseMode mode;
	
	private GridBagConstraints gbc_canvas;



	private Canvas() {
		umlObjectList = new ArrayList<BaseObject>();
		umlLine = new ArrayList<Line>();
		groupList = new ArrayList<Group>();
		
		gbc_canvas = new GridBagConstraints();
		gbc_canvas.insets = new Insets(0, 0, 5, 0);
		gbc_canvas.gridheight = 6;
		gbc_canvas.fill = GridBagConstraints.BOTH;
		gbc_canvas.gridx = 1;
		gbc_canvas.gridy = 0;
		
		addListener();
	}

	public static Canvas getInstance() {
		if (instance == null) {
			instance = new Canvas();
		}
		return instance;
	}

	public void setCurrentMode(BaseMode mod) {
		this.mode = mod;
	}
	//listener for current mode
	public void addListener() {
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}

			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				mode.mousePressed(x, y);
			}

			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				mode.mouseReleased(x, y);
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener(){

			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				mode.mouseDragged(x, y);
			}

			public void mouseMoved(MouseEvent arg0) {}
		});
	}
	
	public GridBagConstraints getGridBagConstraints() {return gbc_canvas;}
	
	
	//object
	public void addObject(BaseObject obj) {
		umlObjectList.add(obj);
	}
	
	public void setObjectID() {
		int lastIndex = umlObjectList.size()-1;
		umlObjectList.get(lastIndex).setOriginObjectID(lastIndex);
	}
	
	public void setIsSelectOnObject(Boolean b) {isSelectOnObject = b;}

	public int getObjectId() {return objectID;}

	public void setObjectIsSelected(Boolean b) {umlObjectList.get(objectID).setIsSelected(b);}

	
	//line
	public void addConnectionLine(Line line) {umlLine.add(line);}
	
	
	//group
	public void setIsSelectOnGroup(Boolean b) {isSelectOnGroup = b;}
	
	public Boolean isSelectOnGroup() {return isSelectOnGroup;}
	
	public void setGroupIsSelected(Boolean b) {groupList.get(groupID).setIsSelected(b);}
	
	
	
	public String getSituation() {return situation;}
	
	

	
	public void setIsDraging(Boolean sid) {isDraging = sid;}
	public Boolean isDraging() {return isDraging;}
	
	
	public Point getLinePosition(String dirction, Point objectLocation, Point objectSize) {
		int x=1, y=1;
		if(dirction.equals("N")) {
			x = objectLocation.x + objectSize.x/2;
			y = objectLocation.y;
		}
		if(dirction.equals("W")) {
			x = objectLocation.x;
			y = objectLocation.y + objectSize.y/2;
		}
		if(dirction.equals("S")) {
			x = objectLocation.x + objectSize.x/2;
			y = objectLocation.y + objectSize.y;
		}
		if(dirction.equals("E")) {
			x = objectLocation.x + objectSize.x;
			y = objectLocation.y + objectSize.y/2;
		}
		return new Point(x, y);
	}
	
	public Point getRightPosition(int x, int y) {
		for(int i = umlObjectList.size()-1; i >= 0; i--) {
			if(!umlObjectList.get(i).isInGroup()) {
				situation = umlObjectList.get(i).findDirection(new Point(x, y));
				if(!situation.equals("miss")) {
					Point objectLocation = umlObjectList.get(i).getPosition();
					Point objectSize = umlObjectList.get(i).getSize();
					objectID = i;
					Point linePosition = getLinePosition(situation, objectLocation, objectSize);
					return linePosition;
				}
			}
		}
		return new Point(-1, -1);
	}
	
	public void setAllObjectUnselected() {
		for(int i = 0; i < umlObjectList.size(); i++)
			umlObjectList.get(i).setIsSelected(false);
		for(int i = 0; i < groupList.size(); i++)
			groupList.get(i).setIsSelected(false);
	}
	
	public Boolean isSelectOnObject(int x, int y) {
		Point p = new Point(x, y);
		for(int i = groupList.size()-1; i >= 0; i--) {
			if(groupList.get(i).isInGroupObject(p)) {
				groupID = i;
				setIsSelectOnGroup(true);
				setIsSelectOnObject(false);
				return true;
			}
		}
		for(int i = umlObjectList.size()-1; i >= 0; i--) {
			if(umlObjectList.get(i).isInObject(p)) {
				objectID = i;
				setIsSelectOnGroup(false);
				setIsSelectOnObject(true);
				return true;
			}
		}
		return false;
	}
	
	public void storeOriginPosition() {
		if(isSelectOnGroup) {
			Point p = groupList.get(groupID).getPosition();
			groupList.get(groupID).setOriginPosition(p);
		}
		if(isSelectOnObject) {
			Point p = umlObjectList.get(objectID).getPosition();
			umlObjectList.get(objectID).setOriginPosition(p);
		}
		for(int i = 0; i < umlLine.size(); i++) {
			Point s = umlLine.get(i).getStartPoint();
			Point e = umlLine.get(i).getEndPoint();
			umlLine.get(i).setOriginStartAndEndPoint(s, e);
		}
	}
	
	public void moveLine(int i, int objectID, Point bias) {
		if(umlLine.get(i).getObjectID_1() == objectID) {
			Point _s = umlLine.get(i).getOriginStartPoint();
			Point s = new Point(_s.x, _s.y);
			s.x += bias.x;
			s.y += bias.y;
			umlLine.get(i).setStartPoint(s);
		}
		if(umlLine.get(i).getObjectID_2() == objectID) {
			Point _e = umlLine.get(i).getOriginEndPoint();
			Point e = new Point(_e.x, _e.y);
			e.x += bias.x;
			e.y += bias.y;
			umlLine.get(i).setEndPoint(e);
		}
	}
	
	public void moveObject(int press_x, int press_y, int drag_x, int drag_y) {
		Point bias = new Point(drag_x-press_x, drag_y-press_y);
		if(isSelectOnGroup) {
			groupList.get(groupID).setAllThingSelected();
			Point _p = groupList.get(groupID).getOriginPostion();
			Point p = new Point(_p.x, _p.y);
			p.x += bias.x;
			p.y += bias.y;
			groupList.get(groupID).setPosition(p, bias);
			for(int i = 0; i < umlObjectList.size(); i++) {
				if(umlObjectList.get(i).isSelected()) 
					for(int j = 0; j < umlLine.size(); j++) 
						moveLine(j, i, bias);
			}
		}
		if(isSelectOnObject) {
			Point p = umlObjectList.get(objectID).getOriginPostion();
			Point _p = new Point(p.x, p.y);
			_p.x += bias.x;
			_p.y += bias.y;
			umlObjectList.get(objectID).setPosition(_p, bias);
			for(int i = 0; i < umlLine.size(); i++)
				moveLine(i, objectID, bias);
		}
	}
	
	public void setPerfectRect(int x1, int y1, int x2, int y2) {
		perfectRect_x = Math.min(x1, x2);
		perfectRect_y = Math.min(y1, y2);
		perfectRect_w = Math.abs(x1-x2);
		perfectRect_h = Math.abs(y1-y2);
	}
	
	
	public void changeObjectIsSelected() {
		if(umlObjectList.get(objectID).isSelected())
			umlObjectList.get(objectID).setIsSelected(false);
		else
			umlObjectList.get(objectID).setIsSelected(true);
		for(int i = 0; i < umlObjectList.size() ; i++)
			if(i!=objectID && umlObjectList.get(i).isSelected())
				umlObjectList.get(i).setIsSelected(false);
		for(int i = 0; i < groupList.size() ; i++)
			groupList.get(i).setIsSelected(false);
	}
	
	public void changeSelectAreaObject() {
		setAllObjectUnselected();
		Point p1 = new Point(perfectRect_x, perfectRect_y);
		Point p2 = new Point(perfectRect_x+perfectRect_w, perfectRect_y+perfectRect_h);
		for(int i = umlObjectList.size()-1; i >= 0; i--)
			if(umlObjectList.get(i).isInObject(p1, p2) && !umlObjectList.get(i).isInGroup())
				umlObjectList.get(i).setIsSelected(true);
		for(int i = groupList.size()-1; i >= 0; i--)
			if(groupList.get(i).isInGroupObject(p1, p2))
				groupList.get(i).setIsSelected(true);
	}
	
	public void changeObjectName() {
		if(isSelectOnObject) {
			if(umlObjectList.get(objectID).isSelected()) {
				String input = JOptionPane.showInputDialog(null,"Please input object name:","Change Object Name",JOptionPane.QUESTION_MESSAGE);
				if(input != null) {
					for(int i = 0; i < umlObjectList.size(); i++)
						if(umlObjectList.get(i).isSelected())
							umlObjectList.get(i).setObjectName(input);
					repaint();
				}
			}
		}
	}
	
	public void group() {
		ArrayList<Integer> tempObjectID = new ArrayList<Integer>();
		ArrayList<Integer> tempGroupID = new ArrayList<Integer>();
		Group composite = new Group();
		for(int i = 0; i < umlObjectList.size(); i++)
			if(umlObjectList.get(i).isSelected())
				tempObjectID.add(i);
		for(int i = 0; i < groupList.size(); i++)
			if(groupList.get(i).isSelected())
				tempGroupID.add(i);
		
		int selectedNumber = tempObjectID.size() + tempGroupID.size();
		if(selectedNumber > 1) {
			for(int i = 0; i < tempObjectID.size(); i++) {
				umlObjectList.get(tempObjectID.get(i)).setIsSelected(false);
				umlObjectList.get(tempObjectID.get(i)).setIsInGroup(true);
				composite.addObject(umlObjectList.get(tempObjectID.get(i)));
			}
			for(int i = 0; i < tempGroupID.size(); i++) {
				groupList.get(tempGroupID.get(i)).setIsSelected(false);
				groupList.get(tempGroupID.get(i)).setIsInGroup(true);
				composite.addObject(groupList.get(tempGroupID.get(i)));
			}
			composite.findGroupBorder();
			groupList.add(composite);
			repaint();
		}
	}
	
	public void ungroup() {
		for(int i = groupList.size()-1; i >= 0; i--) {
			if(groupList.get(i).isSelected()) {
				ArrayList<BaseShape> temp = groupList.get(i).getGroupObjectList();
				for(int j = 0; j < temp.size(); j++)
					temp.get(j).setIsInGroup(false);
				groupList.remove(i);
				break;
			}
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3.5f));
		for(int i = 0; i < umlObjectList.size(); i++)
			umlObjectList.get(i).draw(g2d);
		
		g2d.setStroke(new BasicStroke(1.5f));
		for(int j = 0; j < umlLine.size(); j++)
			umlLine.get(j).draw(g2d);
		
		for(int i = 0; i < umlObjectList.size(); i++)
			if(umlObjectList.get(i).isSelected())
				umlObjectList.get(i).drawPorts(g2d);
		
		for(int i = 0; i < groupList.size(); i++) {
			g2d.setStroke(new BasicStroke(3.5f));
			groupList.get(i).draw(g2d);
		}
		if(isDraging) {
			g2d.setStroke(new BasicStroke(1.0f));
			g2d.drawRect(perfectRect_x, perfectRect_y, perfectRect_w, perfectRect_h);
		}
	}
	

 }