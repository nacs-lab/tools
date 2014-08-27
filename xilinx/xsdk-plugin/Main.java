package edu.harvard.nacs_lab.create_project;

import com.xilinx.sdk.api.XSDK;
import java.io.PrintStream;
import org.eclipse.core.resources.IProject;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class Main
    implements IApplication {

    public Main()
    {
    }

    public Object start(IApplicationContext iapplicationcontext)
        throws Exception
    {
        String s = getHwSpecPath();
        String s1 = getProcName();
        IProject iproject = XSDK.createHwProject("hw_platform", s);
        IProject iproject1 = XSDK.createBSPProject("standalone_bsp", iproject, s1, "standalone");
        IProject iproject2 = XSDK.createXilinxAppProject("hello", iproject1, false);
        if(iproject2.exists())
            System.out.println((new StringBuilder("Successfully created project ")).append(iproject2.getName()).toString());
        return null;
    }

    private void usage()
    {
        System.out.println("This is a simple demo program illustrating the use of Xilinx API's to create a project in workspace.");
        System.out.println("It takes arguments via JVM properties:");
        System.out.println("    -Dcom.xilinx.sdk.api.examples.hwspec=<path to hwspec>");
        System.out.println("    -Dcom.xilinx.sdk.api.examples.procName=<name of processor>");
    }

    private String getHwSpecPath()
    {
        return System.getProperty("com.xilinx.sdk.api.examples.hwspec");
    }

    private String getProcName()
    {
        return System.getProperty("com.xilinx.sdk.api.examples.procName");
    }

    public void stop()
    {
    }
}
