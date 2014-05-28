import org.crsh.cli.Command
import org.crsh.cli.Man
import org.crsh.cli.Usage

@Usage("provides a set of command for interacting with grails runtime")
public class grails {

  @Command
  @Usage("use the grails datasource with the jdbc command")
  @Man("""\
Open a connection to the grails datasource, this connection can then be used by the jdbc command:

% grails datasource
Connected to grails appliation datasource

% jdbc info
TYPE    NAME           VERSION              MAJOR MINOR
--------------------------------------------------------
Product H2             1.3.173 (2013-07-28) 1     3
Product H2 JDBC Driver 1.3.173 (2013-07-28) 1     3

The connection can be closed with the jdbc close command after usage.""")
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