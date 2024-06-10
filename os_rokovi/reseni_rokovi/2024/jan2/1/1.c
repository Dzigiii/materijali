#define  _XOPEN_SOURCE 700
#define  _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>

#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <sys/mman.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdint.h>
#include <wait.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <errno.h>
#include <semaphore.h>
#include <dirent.h>
#include <ftw.h>
#include <utime.h>

#define check_error(expr,userMsg)\
	do {\
		if (!(expr)) {\
			perror(userMsg);\
			exit(EXIT_FAILURE);\
		}\
	} while (0)


time_t now;
time_t max_a;
time_t max_mo;

void processFile2(const char *dirpath) {

    struct dirent *entry;
    DIR *dp = opendir(dirpath);
    check_error(dp != NULL, "opendir failed\n");

    while ((entry = readdir(dp)) != NULL) {
        if (entry->d_type == DT_REG) {

            char* newPath = malloc(strlen(dirpath) + strlen(entry->d_name) + 1);
            check_error(newPath != NULL, "newpath malloc failed\n");

            strcpy(newPath, dirpath);
            strcat(newPath, "/");
            strcat(newPath, entry->d_name);

            struct utimbuf novoVreme;
            novoVreme.actime = now - max_a;
            novoVreme.modtime = now - max_mo;
            check_error(utime(newPath, &novoVreme) != -1, "utime failed\n");

            free(newPath);
        }
        errno = 0;
    }
    check_error(errno != EBADF, "readdir errno ebadf");
    check_error(closedir(dp) != -1, "closedir failed\n");
}

void processFile(const char *dirpath) {

    struct dirent *entry = NULL;
    DIR *dp = opendir(dirpath);
    check_error(dp != NULL, "opendir failed\n");
    errno = 0;
    while ((entry = readdir(dp)) != NULL) {
        if (entry->d_type == DT_REG) {

            char* newPath = malloc(strlen(dirpath) + strlen(entry->d_name) + 1);
            check_error(newPath != NULL, "newpath malloc failed\n");

            strcpy(newPath, dirpath);
            strcat(newPath, "/");
            strcat(newPath, entry->d_name);

            struct stat fInfo;
            check_error(stat(newPath, &fInfo) != -1, "stat failed\n");

            if (now - fInfo.st_atime < max_a) {
                max_a = now - fInfo.st_atime;
            }
            if (now - fInfo.st_mtime < max_mo) {
                max_mo = now - fInfo.st_mtime;
            }
            free(newPath);
        }
        errno = 0;
    }
    check_error(errno != EBADF, "readdir errno ebadf");
    check_error(closedir(dp) != -1, "closedir failed\n");
}



int main (int argc, char ** argv) {


    check_error(argc == 1, "argcs\n");

    int fd = open(".", O_RDONLY);
    check_error( fd != -1, "fd open failed\n");

    check_error(time(&now) != -1, "time failed\n");
    max_a = now;
    max_mo = now;
    processFile(".");
    processFile2(".");




    exit(EXIT_SUCCESS);
}
