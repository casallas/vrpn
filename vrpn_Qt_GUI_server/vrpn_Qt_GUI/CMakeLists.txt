CMAKE_MINIMUM_REQUIRED( VERSION 2.6 )

PROJECT( vrpn_Qt_GUI )

SET( EXECUTABLE_OUTPUT_PATH "${vrpn_Qt_BINARY_DIR}/bin" )


#######################################
# Include Qt
#######################################

# Set up variables for moc
SET( QT_HEADER QAutoGUI.h )
SET( QT_SRC vrpn_Qt_GUI.cpp QAutoGUI.cpp )

INCLUDE_DIRECTORIES( ${CMAKE_CURRENT_BINARY_DIR}
                     ${CMAKE_CURRENT_SOURCE_DIR} )

# Do moc stuff
SET( QT_MOC_SRC )
QT4_WRAP_CPP( QT_MOC_SRC ${QT_HEADER} )
ADD_DEFINITIONS( -DQT_GUI_LIBS -DQT_CORE_LIB -DQT3_SUPPORT )


#######################################
# Build setup 
#######################################

SET( SRC QXmlAutoGUIHandler.h QXmlAutoGUIHandler.cpp
         vrpn_Qt_GUI.xml )

ADD_EXECUTABLE( vrpn_Qt_GUI ${QT_HEADER} ${QT_SRC} ${QT_MOC_SRC} ${SRC} )
ADD_DEPENDENCIES( vrpn_Qt_GUI vrpn_Qt )
TARGET_LINK_LIBRARIES( vrpn_Qt_GUI vrpn_Qt )

ADD_CUSTOM_COMMAND( TARGET vrpn_Qt_GUI
                    PRE_BUILD
                    COMMAND ${CMAKE_COMMAND} -E copy 
                    ${vrpn_Qt_GUI_SOURCE_DIR}/vrpn_Qt_GUI.xml
                    ${vrpn_Qt_BINARY_DIR}/vrpn_Qt_GUI )	

ADD_CUSTOM_COMMAND( TARGET vrpn_Qt_GUI
                    POST_BUILD
                    COMMAND ${CMAKE_COMMAND} -E copy 
                    ${vrpn_Qt_BINARY_DIR}/bin/Release/vrpn_Qt_GUI.exe
                    ${XVREXAMPLE_DIR} )	

