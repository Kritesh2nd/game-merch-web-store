package Service;
public class Support {    
    public String getFirstName(String fullname){
        String firstname=fullname;
        if (fullname.contains(" ")) {
            int firstSpacePosition = fullname.indexOf(" ");
            firstname = fullname.substring(0,firstSpacePosition);
        }
        return firstname;
    }
}
