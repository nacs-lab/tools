find_package(Java)
include(UseJava)

file(GLOB_RECURSE XSDK_JARS
  /tmp/xilinx/xilinx/14.7/ISE_DS/EDK/eclipse/lin64/eclipse/*.jar)

add_jar(edu.harvard.nacs_lab
  SOURCES Main.java plugin.xml
  INCLUDE_JARS ${XSDK_JARS})

install_jar(edu.harvard.nacs_lab
  "${CMAKE_INSTALL_LIBDIR}/xilinx/xsdk-plugin")
