/*
 * 06/05/2016
 *
 * This library is distributed under a modified BSD license.  See the included
 * LICENSE file for details.
 */
package org.fife.ui.rsyntaxtextarea.modes;

import org.fife.ui.rsyntaxtextarea.TokenMaker;
import org.fife.ui.rsyntaxtextarea.TokenTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Unit tests for the {@link AssemblerX86TokenMaker} class.
 *
 * @author Robert Futrell
 * @version 1.0
 */
public class AssemblerX86TokenMakerTest extends AbstractTokenMakerTest2 {


	@BeforeEach
	void setUp() {
	}


	@AfterEach
	void tearDown() {
	}


	@Override
	protected TokenMaker createTokenMaker() {
		return new AssemblerX86TokenMaker();
	}


	@Test
	@Override
	public void testGetLineCommentStartAndEnd() {
		String[] startAndEnd = createTokenMaker().getLineCommentStartAndEnd(0);
		Assertions.assertEquals(";", startAndEnd[0]);
		Assertions.assertNull(null, startAndEnd[1]);
	}


	@Test
	void testPreprocessor() {

		String[] preprocessors = {
				".186",
				".286",
				".286P",
				".287",
				".386",
				".386P",
				".387",
				".486",
				".486P",
				".586",
				".586P",
				".686",
				".686P",
				".8086",
				".8087",
				".ALPHA",
				".BREAK",
				".BSS",
				".CODE",
				".CONST",
				".CONTINUE",
				".CREF",
				".DATA",
				".DATA?",
				".DOSSEG",
				".ELSE",
				".ELSEIF",
				".ENDIF",
				".ENDW",
				".ERR",
				".ERR1",
				".ERR2",
				".ERRB",
				".ERRDEF",
				".ERRDIF",
				".ERRDIFI",
				".ERRE",
				".ERRIDN",
				".ERRIDNI",
				".ERRNB",
				".ERRNDEF",
				".ERRNZ",
				".EXIT",
				".FARDATA",
				".FARDATA?",
				".IF",
				".K3D",
				".LALL",
				".LFCOND",
				".LIST",
				".LISTALL",
				".LISTIF",
				".LISTMACRO",
				".LISTMACROALL",
				".MMX",
				".MODEL",
				".MSFLOAT",
				".NO87",
				".NOCREF",
				".NOLIST",
				".NOLISTIF",
				".NOLISTMACRO",
				".RADIX",
				".REPEAT",
				".SALL",
				".SEQ",
				".SFCOND",
				".STACK",
				".STARTUP",
				".TEXT",
				".TFCOND",
				".UNTIL",
				".UNTILCXZ",
				".WHILE",
				".XALL",
				".XCREF",
				".XLIST",
				".XMM",
				"__FILE__",
				"__LINE__",
				"A16",
				"A32",
				"ADDR",
				"ALIGN",
				"ALIGNB",
				"ASSUME",
				"BITS",
				"CARRY?",
				"CATSTR",
				"CODESEG",
				"COMM",
				"COMMENT",
				"COMMON",
				"DATASEG",
				"DOSSEG",
				"ECHO",
				"ELSE",
				"ELSEIF",
				"ELSEIF1",
				"ELSEIF2",
				"ELSEIFB",
				"ELSEIFDEF",
				"ELSEIFE",
				"ELSEIFIDN",
				"ELSEIFNB",
				"ELSEIFNDEF",
				"END",
				"ENDIF",
				"ENDM",
				"ENDP",
				"ENDS",
				"ENDSTRUC",
				"EVEN",
				"EXITM",
				"EXPORT",
				"EXTERN",
				"EXTERNDEF",
				"EXTRN",
				"FAR",
				"FOR",
				"FORC",
				"GLOBAL",
				"GOTO",
				"GROUP",
				"HIGH",
				"HIGHWORD",
				"IEND",
				"IF",
				"IF1",
				"IF2",
				"IFB",
				"IFDEF",
				"IFDIF",
				"IFDIFI",
				"IFE",
				"IFIDN",
				"IFIDNI",
				"IFNB",
				"IFNDEF",
				"IMPORT",
				"INCBIN",
				"INCLUDE",
				"INCLUDELIB",
				"INSTR",
				"INVOKE",
				"IRP",
				"IRPC",
				"ISTRUC",
				"LABEL",
				"LENGTH",
				"LENGTHOF",
				"LOCAL",
				"LOW",
				"LOWWORD",
				"LROFFSET",
				"MACRO",
				"NAME",
				"NEAR",
				"NOSPLIT",
				"O16",
				"O32",
				"OFFSET",
				"OPATTR",
				"OPTION",
				"ORG",
				"OVERFLOW?",
				"PAGE",
				"PARITY?",
				"POPCONTEXT",
				"PRIVATE",
				"PROC",
				"PROTO",
				"PTR",
				"PUBLIC",
				"PURGE",
				"PUSHCONTEXT",
				"RECORD",
				"REPEAT",
				"REPT",
				"SECTION",
				"SEG",
				"SEGMENT",
				"SHORT",
				"SIGN?",
				"SIZE",
				"SIZEOF",
				"SIZESTR",
				"STACK",
				"STRUC",
				"STRUCT",
				"SUBSTR",
				"SUBTITLE",
				"SUBTTL",
				"THIS",
				"TITLE",
				"TYPE",
				"TYPEDEF",
				"UNION",
				"USE16",
				"USE32",
				"USES",
				"WHILE",
				"WRT",
				"ZERO?",
		};

		assertAllTokensOfType(preprocessors, TokenTypes.PREPROCESSOR);
	}


	@Test
	void testFunctions() {

		String[] functions = {
				"DB",
				"DW",
				"DD",
				"DF",
				"DQ",
				"DT",
				"RESB",
				"RESW",
				"RESD",
				"RESQ",
				"REST",
				"EQU",
				"TEXTEQU",
				"TIMES",
				"DUP",
		};

		assertAllTokensOfType(functions, TokenTypes.FUNCTION);
	}


	@Test
	void testDataTypes() {

		String[] dataTypes = {
				"BYTE",
				"WORD",
				"DWORD",
				"FWORD",
				"QWORD",
				"TBYTE",
				"SBYTE",
				"TWORD",
				"SWORD",
				"SDWORD",
				"REAL4",
				"REAL8",
				"REAL10",
		};

		assertAllTokensOfType(dataTypes, TokenTypes.DATA_TYPE);
	}


	@Test
	void testRegisters() {

		String[] registers = {
				"AL",
				"BL",
				"CL",
				"DL",
				"AH",
				"BH",
				"CH",
				"DH",
				"AX",
				"BX",
				"CX",
				"DX",
				"SI",
				"DI",
				"SP",
				"BP",
				"EAX",
				"EBX",
				"ECX",
				"EDX",
				"ESI",
				"EDI",
				"ESP",
				"EBP",
				"CS",
				"DS",
				"SS",
				"ES",
				"FS",
				"GS",
				"ST",
				"ST0",
				"ST1",
				"ST2",
				"ST3",
				"ST4",
				"ST5",
				"ST6",
				"ST7",
				"MM0",
				"MM1",
				"MM2",
				"MM3",
				"MM4",
				"MM5",
				"MM6",
				"MM7",
				"XMM0",
				"XMM1",
				"XMM2",
				"XMM3",
				"XMM4",
				"XMM5",
				"XMM6",
				"XMM7",
				"CR0",
				"CR2",
				"CR3",
				"CR4",
				"DR0",
				"DR1",
				"DR2",
				"DR3",
				"DR4",
				"DR5",
				"DR6",
				"DR7",
				"TR3",
				"TR4",
				"TR5",
				"TR6",
				"TR7",
		};

		assertAllTokensOfType(registers, TokenTypes.VARIABLE);
	}


	@Test
	void testP3Instructions() {

		String[] instructions = {
				"AAA",
				"AAD",
				"AAM",
				"AAS",
				"ADC",
				"ADD",
				"ADDPS",
				"ADDSS",
				"AND",
				"ANDNPS",
				"ANDPS",
				"ARPL",
				"BOUND",
				"BSF",
				"BSR",
				"BSWAP",
				"BT",
				"BTC",
				"BTR",
				"BTS",
				"CALL",
				"CBW",
				"CDQ",
				"CLC",
				"CLD",
				"CLI",
				"CLTS",
				"CMC",
				"CMOVA",
				"CMOVAE",
				"CMOVB",
				"CMOVBE",
				"CMOVC",
				"CMOVE",
				"CMOVG",
				"CMOVGE",
				"CMOVL",
				"CMOVLE",
				"CMOVNA",
				"CMOVNAE",
				"CMOVNB",
				"CMOVNBE",
				"CMOVNC",
				"CMOVNE",
				"CMOVNG",
				"CMOVNGE",
				"CMOVNL",
				"CMOVNLE",
				"CMOVNO",
				"CMOVNP",
				"CMOVNS",
				"CMOVNZ",
				"CMOVO",
				"CMOVP",
				"CMOVPE",
				"CMOVPO",
				"CMOVS",
				"CMOVZ",
				"CMP",
				"CMPPS",
				"CMPS",
				"CMPSB",
				"CMPSD",
				"CMPSS",
				"CMPSW",
				"CMPXCHG",
				"CMPXCHGB",
				"COMISS",
				"CPUID",
				"CWD",
				"CWDE",
				"CVTPI2PS",
				"CVTPS2PI",
				"CVTSI2SS",
				"CVTSS2SI",
				"CVTTPS2PI",
				"CVTTSS2SI",
				"DAA",
				"DAS",
				"DEC",
				"DIV",
				"DIVPS",
				"DIVSS",
				"EMMS",
				"ENTER",
				"F2XM1",
				"FABS",
				"FADD",
				"FADDP",
				"FBLD",
				"FBSTP",
				"FCHS",
				"FCLEX",
				"FCMOVB",
				"FCMOVBE",
				"FCMOVE",
				"FCMOVNB",
				"FCMOVNBE",
				"FCMOVNE",
				"FCMOVNU",
				"FCMOVU",
				"FCOM",
				"FCOMI",
				"FCOMIP",
				"FCOMP",
				"FCOMPP",
				"FCOS",
				"FDECSTP",
				"FDIV",
				"FDIVP",
				"FDIVR",
				"FDIVRP",
				"FFREE",
				"FIADD",
				"FICOM",
				"FICOMP",
				"FIDIV",
				"FIDIVR",
				"FILD",
				"FIMUL",
				"FINCSTP",
				"FINIT",
				"FIST",
				"FISTP",
				"FISUB",
				"FISUBR",
				"FLD1",
				"FLDCW",
				"FLDENV",
				"FLDL2E",
				"FLDL2T",
				"FLDLG2",
				"FLDLN2",
				"FLDPI",
				"FLDZ",
				"FMUL",
				"FMULP",
				"FNCLEX",
				"FNINIT",
				"FNOP",
				"FNSAVE",
				"FNSTCW",
				"FNSTENV",
				"FNSTSW",
				"FPATAN",
				"FPREM",
				"FPREMI",
				"FPTAN",
				"FRNDINT",
				"FRSTOR",
				"FSAVE",
				"FSCALE",
				"FSIN",
				"FSINCOS",
				"FSQRT",
				"FST",
				"FSTCW",
				"FSTENV",
				"FSTP",
				"FSTSW",
				"FSUB",
				"FSUBP",
				"FSUBR",
				"FSUBRP",
				"FTST",
				"FUCOM",
				"FUCOMI",
				"FUCOMIP",
				"FUCOMP",
				"FUCOMPP",
				"FWAIT",
				"FXAM",
				"FXCH",
				"FXRSTOR",
				"FXSAVE",
				"FXTRACT",
				"FYL2X",
				"FYL2XP1",
				"HLT",
				"IDIV",
				"IMUL",
				"IN",
				"INC",
				"INS",
				"INSB",
				"INSD",
				"INSW",
				"INT",
				"INTO",
				"INVD",
				"INVLPG",
				"IRET",
				"JA",
				"JAE",
				"JB",
				"JBE",
				"JC",
				"JCXZ",
				"JE",
				"JECXZ",
				"JG",
				"JGE",
				"JL",
				"JLE",
				"JMP",
				"JNA",
				"JNAE",
				"JNB",
				"JNBE",
				"JNC",
				"JNE",
				"JNG",
				"JNGE",
				"JNL",
				"JNLE",
				"JNO",
				"JNP",
				"JNS",
				"JNZ",
				"JO",
				"JP",
				"JPE",
				"JPO",
				"JS",
				"JZ",
				"LAHF",
				"LAR",
				"LDMXCSR",
				"LDS",
				"LEA",
				"LEAVE",
				"LES",
				"LFS",
				"LGDT",
				"LGS",
				"LIDT",
				"LLDT",
				"LMSW",
				"LOCK",
				"LODS",
				"LODSB",
				"LODSD",
				"LODSW",
				"LOOP",
				"LOOPE",
				"LOOPNE",
				"LOOPNZ",
				"LOOPZ",
				"LSL",
				"LSS",
				"LTR",
				"MASKMOVQ",
				"MAXPS",
				"MAXSS",
				"MINPS",
				"MINSS",
				"MOV",
				"MOVAPS",
				"MOVD",
				"MOVHLPS",
				"MOVHPS",
				"MOVLHPS",
				"MOVLPS",
				"MOVMSKPS",
				"MOVNTPS",
				"MOVNTQ",
				"MOVQ",
				"MOVS",
				"MOVSB",
				"MOVSD",
				"MOVSS",
				"MOVSW",
				"MOVSX",
				"MOVUPS",
				"MOVZX",
				"MUL",
				"MULPS",
				"MULSS",
				"NEG",
				"NOP",
				"NOT",
				"OR",
				"ORPS",
				"OUT",
				"OUTS",
				"OUTSB",
				"OUTSD",
				"OUTSW",
				"PACKSSDW",
				"PACKSSWB",
				"PACKUSWB",
				"PADDB",
				"PADDD",
				"PADDSB",
				"PADDSW",
				"PADDUSB",
				"PADDUSW",
				"PADDW",
				"PAND",
				"PANDN",
				"PAVGB",
				"PAVGW",
				"PCMPEQB",
				"PCMPEQD",
				"PCMPEQW",
				"PCMPGTB",
				"PCMPGTD",
				"PCMPGTW",
				"PEXTRW",
				"PINSRW",
				"PMADDWD",
				"PMAXSW",
				"PMAXUB",
				"PMINSW",
				"PMINUB",
				"PMOVMSKB",
				"PMULHUW",
				"PMULHW",
				"PMULLW",
				"POP",
				"POPA",
				"POPAD",
				"POPAW",
				"POPF",
				"POPFD",
				"POPFW",
				"POR",
				"PREFETCH",
				"PSADBW",
				"PSHUFW",
				"PSLLD",
				"PSLLQ",
				"PSLLW",
				"PSRAD",
				"PSRAW",
				"PSRLD",
				"PSRLQ",
				"PSRLW",
				"PSUBB",
				"PSUBD",
				"PSUBSB",
				"PSUBSW",
				"PSUBUSB",
				"PSUBUSW",
				"PSUBW",
				"PUNPCKHBW",
				"PUNPCKHDQ",
				"PUNPCKHWD",
				"PUNPCKLBW",
				"PUNPCKLDQ",
				"PUNPCKLWD",
				"PUSH",
				"PUSHA",
				"PUSHAD",
				"PUSHAW",
				"PUSHF",
				"PUSHFD",
				"PUSHFW",
				"PXOR",
				"RCL",
				"RCR",
				"RDMSR",
				"RDPMC",
				"RDTSC",
				"REP",
				"REPE",
				"REPNE",
				"REPNZ",
				"REPZ",
				"RET",
				"RETF",
				"RETN",
				"ROL",
				"ROR",
				"RSM",
				"SAHF",
				"SAL",
				"SAR",
				"SBB",
				"SCAS",
				"SCASB",
				"SCASD",
				"SCASW",
				"SETA",
				"SETAE",
				"SETB",
				"SETBE",
				"SETC",
				"SETE",
				"SETG",
				"SETGE",
				"SETL",
				"SETLE",
				"SETNA",
				"SETNAE",
				"SETNB",
				"SETNBE",
				"SETNC",
				"SETNE",
				"SETNG",
				"SETNGE",
				"SETNL",
				"SETNLE",
				"SETNO",
				"SETNP",
				"SETNS",
				"SETNZ",
				"SETO",
				"SETP",
				"SETPE",
				"SETPO",
				"SETS",
				"SETZ",
				"SFENCE",
				"SGDT",
				"SHL",
				"SHLD",
				"SHR",
				"SHRD",
				"SHUFPS",
				"SIDT",
				"SLDT",
				"SMSW",
				"SQRTPS",
				"SQRTSS",
				"STC",
				"STD",
				"STI",
				"STMXCSR",
				"STOS",
				"STOSB",
				"STOSD",
				"STOSW",
				"STR",
				"SUB",
				"SUBPS",
				"SUBSS",
				"SYSENTER",
				"SYSEXIT",
				"TEST",
				"UB2",
				"UCOMISS",
				"UNPCKHPS",
				"UNPCKLPS",
				"WAIT",
				"WBINVD",
				"VERR",
				"VERW",
				"WRMSR",
				"XADD",
				"XCHG",
				"XLAT",
				"XLATB",
				"XOR",
				"XORPS",
		};

		assertAllTokensOfType(instructions, TokenTypes.RESERVED_WORD);
	}


	@Test
	void testComments() {
		String[] comments = {
				"; This is a comment",
		};
		assertAllTokensOfType(comments, TokenTypes.COMMENT_EOL);
	}
}
