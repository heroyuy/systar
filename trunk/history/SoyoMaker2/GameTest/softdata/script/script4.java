package engine.script;
public class Script4 extends Script{
  public void execute(){
String[] 选择项数组 = new String[4];
选择项数组[0]="是";
选择项数组[1]="否";
int 选择项 = showDialog(选择项数组);
if(选择项数组[选择项].equals("是")){

}
if(选择项数组[选择项].equals("否")){

}

  }
}
