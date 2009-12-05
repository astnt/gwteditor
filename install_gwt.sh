GWT_FOLDER=/home/ast/tools/gwt-2.0.0-rc2
echo $GWT_FOLDER
mvn install:install-file -DgroupId=com.google.gwt -DartifactId=gwt-servlet -Dversion=2.0 -Dpackaging=jar -Dfile=$GWT_FOLDER/gwt-servlet.jar
mvn install:install-file -DgroupId=com.google.gwt -DartifactId=gwt-user -Dversion=2.0 -Dpackaging=jar -Dfile=$GWT_FOLDER/gwt-user.jar
mvn install:install-file -DgroupId=com.google.gwt -DartifactId=gwt-dev -Dversion=2.0 -Dpackaging=jar -Dfile=$GWT_FOLDER/gwt-dev.jar
