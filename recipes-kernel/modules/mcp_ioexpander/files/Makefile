obj -m += mcp_ioexpander.o

ccflags-y += -std=gnu11

all:
	make -C /lib/modues/$(shell uname -r)/build M=($PWD) modules