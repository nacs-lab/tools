find_package(Java)
include(UseJava)

file(GLOB_RECURSE XSDK_JARS
  /tmp/xilinx/xilinx/14.7/ISE_DS/EDK/eclipse/lin64/eclipse/*.jar)

add_jar(edu.harvard.nacsLab_1.0.0
  SOURCES Main.java plugin.xml
  INCLUDE_JARS ${XSDK_JARS})

set(PLUGIN_DIR "${CMAKE_INSTALL_LIBDIR}/xilinx/xsdk-plugin")

install_jar(edu.harvard.nacsLab_1.0.0
  "${PLUGIN_DIR}/plugins")

install(FILES feature.xml
  DESTINATION "${PLUGIN_DIR}/features/edu.harvard.nacsLab.feature_1.0.0")
