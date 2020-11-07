global    _main

          section   .text

_main:    mov       eax, 0x02000004         ; System call for write.
          mov       edi, 1                  ; File handle 1 is stdout.
          mov       rsi, message            ; Address of string to output.
          mov       edx, 13                 ; Number of bytes.
          syscall                           ; Invoke operating system to do the write.
          mov       eax, 0x02000001         ; System call for exit.
          xor       rdi, rdi                ; Exit code 0.
          syscall                           ; Invoke operating system to exit.

          section   .data
message:  db        "Hello, World", 10      ; Note the newline at the end