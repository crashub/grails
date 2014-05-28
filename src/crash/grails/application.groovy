import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.crsh.cli.Command

@Usage("provides a set of commands for interacting with the current application")
public class application {

  @Command
  @Usage("displays the application config")
  public void config(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.flatConfig.each { k,v ->
      context.provide([NAME:k,value:v])
    }
  }

  @Command
  @Usage("displays the application controllers")
  public void controllers(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.controllerClasses.each { c ->
      context.provide([NAME:c.name,"DEFAULT ACTION":c.defaultAction,FULLNAME:c.fullName,URIS:c.URIs as List])
    }
  }

  @Command
  @Usage("displays the application domains")
  public void domains(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.domainClasses.each { c ->
      context.provide([NAME:c.name])
    }
  }

  @Command
  @Usage("displays the application services")
  public void services(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.serviceClasses.each { c ->
      context.provide([NAME:c.name,DATASOURCE:c.datasource,TRANSACTIONAL:c.transactional,FULLNAME:c.fullName])
    }
  }

  @Command
  @Usage("displays the application codecs")
  public void codecs(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.codecClasses.each { c ->
      context.provide([NAME:c.name,FULLNAME:c.fullName])
    }
  }

  @Command
  @Usage("displays the application taglibs")
  public void taglibs(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.tagLibClasses.each { c ->
      context.provide([NAME:c.name,NAMESPACE:c.namespace,"TAG NAMES":c.tagNames])
    }
  }

  @Command
  @Usage("displays the application url mappings")
  public void urlmappings(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.urlMappingsClasses.each { c ->
      context.provide([NAME:c.name,"EXCLUDE PATTERNS":c.excludePatterns?:"",FULLNAME:c.fullName])
    }
  }

  @Command
  @Usage("displays the application bootstraps")
  public void bootstraps(InvocationContext<Map> context) {
    def app = context.attributes.application
    app.bootstrapClasses.each { c ->
      context.provide([NAME:c.name,FULLNAME:c.fullName])
    }
  }
}