/* 
 * This class will provide a Search instance which will yield  a set of 
 * search results based on the success of pattern matching between a 
 * user specified string, and files within a given directory. 
 *
 * @author     Nathaniel Swan
 */

import java.util.List;
import java.io.File;
import java.util.ArrayList;


public class Search{
   
    private String query;
    private String topLevelDirectory;
    private Boolean useRegex;
    private Boolean useInnerSearch;
    private int searchTime;
    private int depthSearched;
    private int filesMatched;
    private int directoriesMatched;

    public List<String> list = new ArrayList<String>(); //DEBUG
    
    public Search(){
        this.query = "";
        this.topLevelDirectory = "";
        this.searchTime = 0; 
        this.depthSearched = 0;
        this.filesMatched = 0;
        this.directoriesMatched = 0;
        this.useInnerSearch = false;
        this.useRegex = false;
    }

    public void addMatch(String match){
        this.list.add(match);
        System.out.println("Added: " + match);
        this.filesMatched += 1;
    }

    public void setQuery(String input){
        this.query = input;
    }

    public void setTopLevelDirectory(String topdir){
        topLevelDirectory = topdir;
    }

    public void setInnerSearch(){
        this.useInnerSearch = true;
    }

    public void setRegex(){
        this.useRegex = true;
    }
    
   // public static File[] listFilesMatching(File root, String regex) {
   //     if(!root.isDirectory()) {
   //         throw new IllegalArgumentException(root+" is no directory.");
   //     }
   //     final Pattern p = Pattern.compile(regex); // careful: could also throw an exception!
   //     return root.listFiles(new FileFilter(){
   //         @Override
   //         public boolean accept(File file) {
   //             return p.matcher(file.getName()).matches();
   //         }
   //     });
   // }
    
    public void startSearch(){


    }
}

