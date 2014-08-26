#!/bin/bash

CMAKE_CURRENT_BINARY_DIR=$1

[[ "${CMAKE_CURRENT_BINARY_DIR}/.clear-dir-stamp" -ot \
    "${CMAKE_CURRENT_BINARY_DIR}/.configure-stamp" ]] || exit 0

rm -rf "${CMAKE_CURRENT_BINARY_DIR}"/symlinks{,_stamps}/
mkdir -p "${CMAKE_CURRENT_BINARY_DIR}"/symlinks{,_stamps}/
touch "${CMAKE_CURRENT_BINARY_DIR}/.clear-dir-stamp"
