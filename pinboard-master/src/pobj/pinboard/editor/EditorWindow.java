package pobj.pinboard.editor;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface,ClipboardListener{

	private Board board;
	private Tool tool = new ToolRect(); //outil courant

	private Color color = Color.BLACK;
	private Selection selection = new Selection();
	private Clipboard clipBoard =Clipboard.getInstance();
	private CommandStack commandeStack = new CommandStack();
	private MenuItem delete,paste,copy,undo,redo;

	public EditorWindow(Stage stage){
		board = new Board();
		stage.setTitle("Mouahahaha");

		Menu file = new Menu("File");
		MenuItem New = new MenuItem("New");
		MenuItem Close = new MenuItem("Close");
		file.getItems().addAll(New, Close);

		Menu edit = new Menu("Edit");
		Menu tools = new Menu("Tools");
		MenuItem Box = new MenuItem("Box");
		tools.getItems().addAll(Box);



		Button box = new Button("Box");
		Button ellipse = new Button("ellipse");
		Button img = new Button("Img...");
		Button selecte = new Button("Selectrioner");

		ToolBar toolBar = new ToolBar(box, ellipse, img ,selecte);

		MenuItem groupes = new MenuItem("Grouping");
		MenuItem degroupes = new MenuItem("Degrouper");

		Menu toolGroup = new Menu("Group/degroupe");
		toolGroup.getItems().addAll(groupes,degroupes);
		
		Menu toolSave = new Menu("Save/charger");
		MenuItem save = new MenuItem("Save");
		MenuItem charger = new MenuItem("Charger");
		toolSave.getItems().addAll(save,charger);
		
		delete = new MenuItem("Delete");
		paste = new MenuItem("Paste");
		copy = new MenuItem("Copy");
		undo= new MenuItem("undo");
		redo = new MenuItem("redo");
		edit.getItems().addAll(delete,paste,copy,undo,redo);

		Rectangle recJaune = new Rectangle(30,30);
		Rectangle recRouge = new Rectangle(30,30);
		Rectangle recVert = new Rectangle(30,30);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(file, edit, tools,toolGroup,toolSave);

		recJaune.setFill(Color.YELLOW);
		recRouge.setFill(Color.RED);
		recVert.setFill(Color.GREEN);

		ToolBar colorBar = new ToolBar(recJaune,recRouge,recVert);

		VBox vbox = new VBox();

		Canvas canvas = new Canvas(500, 500);

		Label label = new Label("Filled Rectangle tool");


		selecte.setOnAction((e)->{
			this.tool = new ToolSelection() ;
		});

		
		
		New.setOnAction((e)-> {
			new EditorWindow(new Stage());
		});

		Close.setOnAction((e)-> {
			stage.close();
			clipBoard.removeListener(this);
		});

		Box.setOnAction((e)->{
			this.tool = new ToolRect();
		});
		
		
		charger.setOnAction((e)-> {
			FileChooser f = new FileChooser();
			f.setTitle("Choisir un fichier.");
			f.getExtensionFilters().addAll(new ExtensionFilter("*.bin"));
			File selectedFile = f.showOpenDialog(stage);
			if (selectedFile != null)
			this.load(selectedFile.getAbsolutePath());	
		});
		
		save.setOnAction((e)-> {
			FileChooser f = new FileChooser();
			f.setTitle("Choisir un Emplacement et le nom du fichier.");
			File selectedFile = f.showSaveDialog(stage);
			if (selectedFile != null) 
			this.load(selectedFile.getAbsolutePath());	
		});


		box.setOnAction((e)->{
			this.tool = new ToolRect();
		});

		ellipse.setOnAction((e)->{
			this.tool = new ToolEllipse();
		});

		groupes.setOnAction((e)->{
			CommandGroup group=new CommandGroup(this,selection.getContents());
			group.execute();
			commandeStack.addCommand(group);

		});

		degroupes.setOnAction((e)->{
			for( Clip c : selection.getContents())
				if( c instanceof ClipGroup ) {
					CommandUngroup cp = new CommandUngroup(this,(ClipGroup)c);
					cp.execute();
					commandeStack.addCommand(cp);
				}
		});

		canvas.setOnMousePressed((e)-> {
			tool.press(this, e);
		});

		recJaune.setOnMousePressed((e)->{
			color=Color.YELLOW;
		});
		recRouge.setOnMousePressed((e)->{
			color=Color.RED;
		});

		recVert.setOnMousePressed((e)->{
			color=Color.GREEN;
		});

		img.setOnAction((e)->{
			FileChooser f = new FileChooser();
			f.setTitle("Choisir un fichier.");
			f.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
			File selectedFile = f.showOpenDialog(stage);
			if (selectedFile != null) {
				tool = new ToolImage(selectedFile);
			}

		});

		canvas.setOnMouseDragged((e)-> {
			tool.drag(this, e);
			board.draw(canvas.getGraphicsContext2D());
			tool.drawFeedback(this, canvas.getGraphicsContext2D());
		});

		canvas.setOnMouseReleased((e)-> {
			tool.release(this, e);
			tool.drawFeedback(this, canvas.getGraphicsContext2D());
			board.draw(canvas.getGraphicsContext2D());
			clipBoard.notifyListners();
		});

		delete.setOnAction((e)->{
			board.draw(canvas.getGraphicsContext2D());
			clipBoard.clear();

		});
		paste.setOnAction((e)->{
			board.addClip(clipBoard.copyFromClipboard());
			board.draw(canvas.getGraphicsContext2D());

		});

		copy.setOnAction((e)->{
			clipBoard.copyToClipboard(selection.getContents());
		});

		undo.setOnAction((e)->{
			commandeStack.undo();
			clipBoard.notifyListners();
			board.draw(canvas.getGraphicsContext2D());
			
		});
		redo.setOnAction((e)->{
			commandeStack.redo();
			clipBoard.notifyListners();
			board.draw(canvas.getGraphicsContext2D());
				
		});

		clipBoard.addListener(this);
		
		clipboardChanged();
		vbox.getChildren().addAll(menuBar, toolBar, canvas, label,colorBar);
		Scene scene  = new Scene(vbox);
		stage.setScene(scene);
		stage.show();

	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Selection getSelection() {
		// TODO Auto-generated method stub
		return selection ;
	}

	@Override
	public CommandStack getUndoStack() {
		// TODO Auto-generated method stub
		return commandeStack;
	}

	@Override
	public Color getCurrentColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void clipboardChanged() {
		if(clipBoard.isEmpty()) 
			paste.setDisable(true);
		else 
			paste.setDisable(false);

		if(selection.getContents().isEmpty()) {
			copy.setDisable(true);
			delete.setDisable(true);
		}
		else {
			copy.setDisable(false);
			delete.setDisable(false);
		}
		if(commandeStack.isUndoEmpty())
			undo.setDisable(true);
		else
			undo.setDisable(false);

		if(commandeStack.isRedoEmpty())
			redo.setDisable(true);
		else
			redo.setDisable(false);	

		//clipBoard.notify();
	}
	
	
	public void save(String Path) {
		
		    try {
		    	ObjectOutputStream objectOutputStream =new ObjectOutputStream(new FileOutputStream(Path));
		    	objectOutputStream.writeObject(this.board);
				 objectOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
		
	}
	public void load(String Path) {
	    ObjectInputStream objectInputStream;
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(Path));
			 Board boardRead = (Board) objectInputStream.readObject();
			    objectInputStream.close();
			    this.board.clear();
			    this.board=boardRead;
			    
			    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	   
	}

}
