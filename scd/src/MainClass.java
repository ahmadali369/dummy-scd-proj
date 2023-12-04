import javax.swing.SwingUtilities;

import bll.classes.BLLFacade;
import bll.classes.BooksBLO;
import bll.classes.PoemBLO;
import bll.classes.RootsBLO;
import bll.classes.TokenBLO;
import bll.classes.VerseBLO;
import bll.interfaces.IBLLFacade;
import bll.interfaces.IBooksBLO;
import bll.interfaces.IPeomBLO;
import bll.interfaces.IRootsBLO;
import bll.interfaces.ITokenBLO;
import bll.interfaces.IVerseBLO;
import dal.classes.BooksDAO;
import dal.classes.DalFacade;
import dal.classes.PoemDAO;
import dal.classes.RootDAO;
import dal.classes.TokensDAO;
import dal.classes.VerseDAO;
import dal.interfaces.IBookDAO;
import dal.interfaces.IDalFacade;
import dal.interfaces.IPoemDAO;
import dal.interfaces.IRootDAO;
import dal.interfaces.ITokenDAO;
import dal.interfaces.IVerseDAO;
import pl.BooksPO;

public class MainClass {
	public static void main(String[] args) {

		IPoemDAO poemDAO = new PoemDAO();
		IBookDAO bookDAO = new BooksDAO();
		IRootDAO rootDAO = new RootDAO();
		ITokenDAO tokenDAO = new TokensDAO();
		IVerseDAO verseDAO = new VerseDAO();

		IDalFacade facadeDAL = DalFacade.getInstance(poemDAO, bookDAO, rootDAO, tokenDAO, verseDAO);

		IBooksBLO booksBLO = new BooksBLO(facadeDAL);
		IPeomBLO peomBLO = new PoemBLO(facadeDAL);
		IRootsBLO rootsBLO = new RootsBLO(facadeDAL);
		ITokenBLO tokenBLO = new TokenBLO(facadeDAL);
		IVerseBLO verseBLO = new VerseBLO(facadeDAL);

		IBLLFacade facadeBLL = BLLFacade.getInstance(booksBLO, peomBLO, rootsBLO, tokenBLO, verseBLO);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				BooksPO booksPO = new BooksPO(facadeBLL);
				booksPO.setVisible(true);
			}
		});

	}

}
