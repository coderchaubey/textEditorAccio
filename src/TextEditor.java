//imported automatically when we initialized Jframe below
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//implenting action listener interface so that we are able to use its methods of creating an event
//after which its method is overidden below in the last part
public class TextEditor implements ActionListener {
    //declaration for creating the frame for our texteditor
    JFrame frame;

    //declaration for textarea to write
    JTextArea textArea;

    //declaration for creating a menu bar
    //remember this is not included in our frame now
    //we have to include it after initializing in the constructor
    JMenuBar menuBar;

    //declaration for creating menus in the menu bar
    //we have to include it to the menubar
    //and then menubar will have those functions which will again be added to the Jfame
    JMenu file,edit;

    //declaration for creating menu items in the file menu and edit menu
    JMenuItem newfile,openfile,savefile;
    JMenuItem cut,copy,paste,selectAll,close;


    //creating a constructor for texteditor object
    TextEditor (){

        //initialized the Jframe;
        frame=new JFrame();

        //initialized JtextArea
        textArea=new JTextArea();

        //adding textarea to the jFrame
        frame.add(textArea);


        //initializing Jmenubar;
        menuBar=new JMenuBar();

        //initialized the menus
        //with parameters
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //initialized the file menu items
        newfile=new JMenuItem("newfile");
        openfile=new JMenuItem("openfile");
        savefile=new JMenuItem("savefile");

        //adding actionlisteners for creating an event when we click on the list items
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        //adding the file menu items
        //in the file menu
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        //initialized the edit menu items
        cut=new JMenuItem("cut");
        copy=new JMenuItem("copy");
        paste=new JMenuItem("paste");
        selectAll=new JMenuItem("selectAll");
        close=new JMenuItem("close");

        //adding actionlisteners for creating an event when we click on the list items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //adding the edit menu items
        //in the edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        //after initializing menus
        //we also have to add it to the menubar
        menuBar.add(file);
        menuBar.add(edit);

        //now we have to add this bar to our beautiful frame that we created
        //this is the inbuilt function under Jframe class works as a setter
        //it includes the menubar
        frame.setJMenuBar(menuBar);

        //now we have to setBounds for our frame
        //dimensions, height and width
        // below is the method to do so
        frame.setBounds(100,100,800,500);
        // creating a method to make the frame visible
        frame.setVisible(true); //uptil here if we run then we will be getting an empty frame

    }

    public static void main(String[] args) {

        //object created for texteditor
                TextEditor textEditor=new TextEditor();
    }
//this is the overridden function of the interface actionListener
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    //now if anywhere the action is performed then this function will be invoked
        //we first have to check from where is this function been called below is the method to do so
        if(actionEvent.getSource()==cut){
            //performs action according to cut event
            //using the method of the textArea itself
            textArea.cut();
        }

    //if copy is clicked
        if(actionEvent.getSource()==copy){
            //perform copy operation under textarea
            textArea.copy();
        }
        //if paste is clicked
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        //if selectAll is clicked
        if (actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        //if close is clicked
        if (actionEvent.getSource()==close){
            System.exit(0);
        }
        //source is newfile
        if(actionEvent.getSource()==newfile){
            //creating a new instance for texteditor class as it will create a new window
            //logic is same as notepad
            TextEditor newfile=new TextEditor();
        }
        //if source is open file then logic will be
        // first we need to get the filechooser
        //then we need to get the text file stored in our computer
        if (actionEvent.getSource()==openfile){
            //creating the instance of a Jfilechooser class inside swing library of java
            JFileChooser fileChooser=new JFileChooser("C:");

            //get the approve button that is open and cancel button in the dialog box
            //this is in integer format
            int chooseOption=fileChooser.showOpenDialog(null);
            //if chooseOption equals to Approve
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //get the selected file using file class instance
                File file=fileChooser.getSelectedFile();
                //get the path to the selected file
                String filepath=file.getPath();

                // now we are adding try and catch components for error(excepiton) handling
                try{
                //creating a bufferedreader instance and passing Filereader for reading files from
                    //the filepath line by line
                    //can also use scanner class
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(filepath));

                    //creating string objects for storing line from reader
                    String intermediate="";
                    StringBuilder output= new StringBuilder();

                    //read content line by line
                    while((intermediate= bufferedReader.readLine())!=null){
                        output = new StringBuilder((output + intermediate) + "\n");
                    }

                    //setting output text to the textarea using the inbuilt method
                    textArea.setText(output.toString());

                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        }
        //if source is save file
        if (actionEvent.getSource()==savefile){
            //save a file
            //create a filechooser
            JFileChooser fileChooser=new JFileChooser("C:");
            //here is the change that we make from the previous open file code
            int chooseOption=fileChooser.showSaveDialog(null);
            //same as before
            if(chooseOption==JFileChooser.APPROVE_OPTION){

                //creating a new file
                //not like before where we just have to get file
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                //creating error handling cases
                try{
                //here we are needed to write the file not read anymore
                    //so we will be creating a buffered Writer instance for the same
                    BufferedWriter outfile=new BufferedWriter(new FileWriter(file));

                    //now we will not be reading file from textArea
                    //we will be writing
                    textArea.write(outfile);

                    //we have to close our BW
                    outfile.close();
                }catch (Exception exception){
                exception.printStackTrace();
                }
            }
        }
    }
}