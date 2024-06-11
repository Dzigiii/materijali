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

#define RDEND (0)
#define WREND (1)
#define MAX_LINE_LENGTH (1024)

#define checkError(cond,msg)do{\
    if(!(cond)){\
        perror(msg);\
        exit(EXIT_FAILURE);\
    }\
}while(0)


int main(int argc,char** argv){
    char *file_path=getenv("REG_FAJL");
    checkError(file_path!=NULL,"File path");

    printf("Putanja je %s\n", file_path);
    int cl2par[2];
    checkError(pipe(cl2par)!=-1,"Pipe");

    pid_t pid=fork();
    checkError(pid!=-1,"pid");

    if (pid ==0) {//child
        close(cl2par[RDEND]);
        checkError(dup2(cl2par[WREND], STDOUT_FILENO)!=-1,"dup2");
        close(cl2par[WREND]);
        checkError( execlp("cat", "cat", file_path, NULL)!=-1,"execlp");
    } else {//parent
        int status=0;
        checkError(wait(&status)!=-1,"wait");
        if (WIFEXITED(status)&&WEXITSTATUS(status)==EXIT_SUCCESS) {
            close(cl2par[1]);
            FILE* stream = fdopen(cl2par[RDEND], "r");
            checkError(stream != NULL, "fdopen\n");
            char* line = NULL;
            size_t duzinaLinije = 0;
            int max_length = -1;
            while (getline(&line, &duzinaLinije, stream) != -1) {
                int length = strlen(line);
                if (length > max_length) {
                    max_length = length;
                }
            }
            close(cl2par[0]);
            free(line);
            printf("Dužina najveće linije je %d.\n", max_length);
            fclose(stream);
        }
        else {
            printf("Greska pri citanju!\n");
        }
    }

    return 0;
}