import org.codehaus.groovy.grails.plugins.GrailsPluginManager
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext

import javax.smartcardio.CommandAPDU;

@Usage("provides a set of command for interacting with grails plugins")
public class plugin {

  private GrailsPluginManager getPluginManager(InvocationContext<?> context) {
    return context.attributes.factory.parentBeanFactory.getBean("pluginManager")
  }

  @Command
  @Usage("list all plugins")
  public void ls(InvocationContext<Map> context) {
    def mgr = getPluginManager(context);
    def userPlugins = mgr.userPlugins as List;
    mgr.allPlugins.each { plugin ->
      def name = plugin.name
      def failed = mgr.getFailedPlugin(name) != null
      context.provide([
          NAME:name,
          VERSION:plugin.version,
          FAILED:failed,
          ENABLED:plugin.enabled,
          USER:userPlugins.contains(plugin),
          PATH:mgr.getPluginPath(name)
      ])
    }
  }

  @Command
  @Usage("provide detailled information about a specific plugin")
  public void info(InvocationContext<Map> context, @Argument @Required @Usage("the plugin name") String name) {
    def mgr = getPluginManager(context);
    def plugin = mgr.getAllPlugins().find { it.name == name }
    if (plugin == null) {
      throw new ScriptException("No such plugin $name")
    } else {
      [
          name:plugin.name,
          version:plugin.version,
          enabled:plugin.enabled,
          path:mgr.getPluginPath(name),
          descriptor:plugin.descriptor.filename,
          fileSystemName:plugin.fileSystemName,
          loadAfter:plugin.loadAfterNames as List,
          loadBefore:plugin.loadBeforeNames as List,
          dependencies:plugin.dependencyNames as List,
          evictions:plugin.evictionNames as List,
          observed:plugin.observedPluginNames as List,
          artifacts:plugin.providedArtefacts.collect{it.name},
          watchedResourcePatterns:plugin.watchedResourcePatterns.collect{it.pattern},
      ].each { k,v ->
        context.provide([NAME:k,VALUE:v])
      }
    }
  }

  @Command
  @Usage("provide the properties about a specific plugin")
  public void properties(InvocationContext<Map> context, @Argument @Required @Usage("the plugin name") String name) {
    def mgr = getPluginManager(context);
    def plugin = mgr.getAllPlugins().find { it.name == name }
    if (plugin == null) {
      throw new ScriptException("No such plugin $name")
    } else {
      plugin.properties.each { k,v ->
        context.provide([NAME:k,VALUE:v])
      }
    }
  }

  @Command
  @Usage("refresh a list of plugins")
  public void refresh(@Argument @Usage("the plugins to refresh") List<String> name) {
    def mgr = getPluginManager(context);
    name.each(mgr.&refreshPlugin)
  }
}