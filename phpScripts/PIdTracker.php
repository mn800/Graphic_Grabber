<?php 
$myfile = fopen("PIDTracker.txt", "r");
echo fread($myfile,filezise("webdictionary.txt"));
fclose($myfile);
?>