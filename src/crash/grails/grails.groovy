import org.crsh.cli.Command

public class grails {

  @Command
  public String datasource() {
    def ds =  context.attributes.factory.getBean("dataSource")
    if (ds == null) {
      throw new ScriptException("No datasource");
    } else {
      connection = ds.connection
      return "Connected to grails appliation datasource\n"
    }
  }
}