global    _main

          section   .text

_main:    mov       eax, 0x02000004         ; Set some value in EAX register.
_main1:   mov       eax, 0x12345678         ; If you look in IDA you will see endianness.
_main2:   mov       rax, 0x12000004         ; NASM will automatically translate it to EAX for optimisation.
_main3:   mov       rax, strict 0x12000004  ; Strict forces NASM to emit the exact RAX. 
                                            ; register without optimising to EAX
_main4:   mov       rsi, 0x12345678         ; Another example of mov. 
_main5:   mov       rsi, message            ; Another example of mov. Look it up in IDA.
_main6:   dec       eax                     ; Decreases value of EAX.
          dec       ebx                     ; Decreases value of EBX.
          dec       ecx                     ; You can see in IDA how different registers
          dec       edx                     ; get translated to slightly different machine code.
          mov       ebx, 3                  ; Loop counter, initially equal 3.
_print:
          mov       eax, 0x02000004         ; System call for write.
          mov       edi, 1                  ; File handle 1 is stdout.
          mov       rsi, message            ; Address of string to output.
          mov       edx, 13                 ; Number of bytes.
          syscall                           ; Invoke operating system to do the write.
          dec       ebx                     ; Decrease loop counter.
          cmp       ebx, 0                  ; Compare loop counter with zero.
          jg        _print                  ; Repeat loop if counter greater than zero.
          jg        _main                   ; This will never happen. Just here so you can comapre two
                                            ; jump statements in IDA and see the difference in addresses.
          mov       eax, 0x02000001         ; System call for exit.
          xor       rdi, rdi                ; Exit code 0.
          syscall                           ; Invoke operating system to exit.
          jmp       _print                  ; More examples of jumps that you can lookup in IDA
          jmp       _main                   ; More examples of jumps that you can lookup in IDA
          jmp       _main1                  ; More examples of jumps that you can lookup in IDA
          jmp       _main2                  ; More examples of jumps that you can lookup in IDA
          jmp       _main3                  ; More examples of jumps that you can lookup in IDA
          jmp       _main4                  ; More examples of jumps that you can lookup in IDA
          jmp       _main5                  ; More examples of jumps that you can lookup in IDA
          jmp       _main6                  ; More examples of jumps that you can lookup in IDA
          jmp       message                 ; More examples of jumps that you can lookup in IDA
          jmp       rax                     ; More examples of jumps that you can lookup in IDA
          jmp       rbx                     ; More examples of jumps that you can lookup in IDA
          jmp       rcx                     ; More examples of jumps that you can lookup in IDA
          jmp       rdx                     ; More examples of jumps that you can lookup in IDA

          section   .data
message:  db        "Hello, World", 10      ; Note the newline at the end