package playerc;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1 -- created: 2005-06-17 modified: 2017-09-04
 */
public abstract class Scanner {
	private PushbackReader source;
	private Token lookahead;
	private int lineNumber;

	public Scanner(Reader in) {
		source = new PushbackReader(in);
		lookahead = null;
		lineNumber = 1;
	}

	public Token peek() throws IOException, LexicalException {
		if (lookahead == null)
			lookahead = getNextToken();
		return lookahead;
	}

	public Token nextToken() throws IOException, LexicalException {
		Token answer;
		if (lookahead != null) {
			answer = lookahead;
			lookahead = null;
		} else
			answer = getNextToken();
		return answer;
	}

	public int lineNumber() {
		return lineNumber;
	}

	protected int getNextByte() throws IOException {
		int x;
		while (true) {
			x = source.read();
			if (x == '\n')
				lineNumber++;
			if (x == -1)
				return -1;
			if (!isWhitespace((char) x))
				return x;
		}
	}

	public boolean isWhitespace(char c) {
		return c == ' ' || c == '\b' || c == '\f' || c == '\n' || c == '\r' || c == '\t';
	}

	public boolean isNumericDigit(char c) {
		return ('0' <= c) && (c <= '9');
	}

	public boolean isLetter(char c) {
		return ((65 <= c) && (c <= 90)) || ((97 <= c) && (c <= 122));
	}

	public boolean isStringChar(char c) {
		return ((32 <= c) && (c <= 126) && (c != '"'));
	}

	protected char read() throws IOException {
		return (char) source.read();
	}

	protected void unread(char c) throws IOException {
		source.unread(c);
	}

	protected abstract Token getNextToken() throws IOException, LexicalException;
}