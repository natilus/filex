/* 
 * This class will provide a Search instance which will yield  a set of 
 * search results based on the success of pattern matching between a 
 * user specified string, and files within a given directory. 
 *
 * @author     Nathaniel Swan
 */

import java.util.List;
import java.io.File;
import java.lang.String;
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
    private List<SearchResult> results;
    
    public Search(String query, File path, Boolean regex, Boolean inner){
        setQuery(query);
        setTopLevelDirectory(path.toString()); 
        this.searchTime = 0; 
        this.depthSearched = 0;
        this.filesMatched = 0;
        this.directoriesMatched = 0;
        this.useInnerSearch = inner;
        this.useRegex = regex;
        this.results = new ArrayList<SearchResult>();
        startSearch(query,path);
    }
   
    public List<SearchResult> getSearchResults(){
        return results;
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

    public void startSearch(String name, File path){
        File[] list = path.listFiles();
        name = name.toLowerCase();
        if(list!=null)
        for (File fil : list){
            
            String fType = "";
            String fileName = fil.toString(); 
            
            if(fileName.toLowerCase().contains(name)){

                if (fil.isDirectory())
                    fType = "D";                
                else
                    fType = "F";
                
                results.add(new SearchResult(fType, fileName));
            }
        }
    }
}

