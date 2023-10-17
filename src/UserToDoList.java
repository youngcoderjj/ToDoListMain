public class UserToDoList {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // name of constuctor must be the same as the class
    public UserToDoList(String name, int age) {
        this.name = name;
        this.age = age;
    }
     public UserToDoList() {
      
    }

    

}