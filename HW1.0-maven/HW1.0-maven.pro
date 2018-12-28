#java -jar proguard/proguard-base-6.0.2.jar @HW1.0-maven.pro

-injars       ./target/HW1.0-maven.jar
-outjars      ./target/HW1.0-maven-release-commandLine.jar

-printmapping pgmapout.map
-dontwarn

-keep public class ru.otus.hw10.Main {
       public static void main(java.lang.String[]);
}