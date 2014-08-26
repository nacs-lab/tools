configure_file(xilinx.in xilinx @ONLY)
configure_file(xplanahead.in xplanahead @ONLY)

install(FILES "${CMAKE_CURRENT_BINARY_DIR}/xilinx"
  "${CMAKE_CURRENT_BINARY_DIR}/xplanahead"
  DESTINATION "${CMAKE_INSTALL_BINDIR}"
  PERMISSIONS OWNER_READ OWNER_WRITE OWNER_EXECUTE
  GROUP_READ GROUP_EXECUTE WORLD_READ WORLD_EXECUTE)

install(FILES xilinx-fix-install xilinx-env xilinx-run
  planahead-run planahead-bitgen planahead-synth _xilinx-run
  planahead-export-hw
  DESTINATION "${CMAKE_INSTALL_LIBEXECDIR}/xilinx"
  PERMISSIONS OWNER_READ OWNER_WRITE OWNER_EXECUTE
  GROUP_READ GROUP_EXECUTE WORLD_READ WORLD_EXECUTE)

execute_process(COMMAND touch "${CMAKE_CURRENT_BINARY_DIR}/.configure-stamp")

add_custom_target(clear-symlink-dir
  COMMAND "${CMAKE_CURRENT_SOURCE_DIR}/clear-symlink-dir.sh"
  "${CMAKE_CURRENT_BINARY_DIR}")

# TODO? use relative path here.
foreach(execname planAhead xsdk)
  add_custom_command(
    OUTPUT "${CMAKE_CURRENT_BINARY_DIR}/symlinks_stamps/${execname}"
    COMMAND ln -sfT "${CMAKE_INSTALL_FULL_LIBEXECDIR}/xilinx/_xilinx-run"
    "${CMAKE_CURRENT_BINARY_DIR}/symlinks/${execname}"
    COMMAND touch "${CMAKE_CURRENT_BINARY_DIR}/symlinks_stamps/${execname}"
    DEPENDS clear-symlink-dir)
  add_custom_target("create-symlink-${execname}" ALL
    DEPENDS "${CMAKE_CURRENT_BINARY_DIR}/symlinks_stamps/${execname}")
endforeach()

install(DIRECTORY "${CMAKE_CURRENT_BINARY_DIR}/symlinks/"
  DESTINATION "${CMAKE_INSTALL_BINDIR}")

# install(FILES planahead.desktop
#   DESTINATION "${CMAKE_INSTALL_DATAROOTDIR}"
#   PERMISSIONS OWNER_READ OWNER_WRITE GROUP_READ WORLD_READ)
