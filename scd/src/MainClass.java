import javax.swing.SwingUtilities;

import ParameterObjects.BloPO;
import ParameterObjects.DaoPO;
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
import dal.classes.DalFacade;
import dal.interfaces.IDalFacade;
import pl.BooksPO;

public class MainClass {
	public static void main(String[] args) {

//		IPoemDAO poemDAO = new PoemDAO();
//		IBookDAO bookDAO = new BooksDAO();
//		IRootDAO rootDAO = new RootDAO();
//		ITokenDAO tokenDAO = new TokensDAO();
//		IVerseDAO verseDAO = new VerseDAO();
//		IDalFacade facadeDAL = DalFacade.getInstance(poemDAO, bookDAO, rootDAO, tokenDAO, verseDAO);

//		IBooksBLO booksBLO = new BooksBLO(facadeDAL);
//		IPeomBLO peomBLO = new PoemBLO(facadeDAL);
//		IRootsBLO rootsBLO = new RootsBLO(facadeDAL);
//		ITokenBLO tokenBLO = new TokenBLO(facadeDAL);
//		IVerseBLO verseBLO = new VerseBLO(facadeDAL);

//		IBLLFacade facadeBLL = BLLFacade.getInstance(booksBLO, peomBLO, rootsBLO, tokenBLO, verseBLO);

		DaoPO daoPo = new DaoPO();
		IDalFacade facadeDAL = DalFacade.getInstance(daoPo);

		BloPO bloPO = new BloPO(facadeDAL);
		IBLLFacade facadeBLL = BLLFacade.getInstance(bloPO);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				BooksPO booksPO = new BooksPO(facadeBLL);
				booksPO.setVisible(true);
			}
		});

	}

}
