package ParameterObjects;

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
import dal.interfaces.IDalFacade;

public class BloPO {

	IDalFacade facadeDAL;

	IBooksBLO booksBLO;
	IPeomBLO peomBLO;
	IRootsBLO rootsBLO;
	ITokenBLO tokenBLO;
	IVerseBLO verseBLO;

	public BloPO(IDalFacade facadeDAL) {

		booksBLO = new BooksBLO(facadeDAL);
		peomBLO = new PoemBLO(facadeDAL);
		rootsBLO = new RootsBLO(facadeDAL);
		tokenBLO = new TokenBLO(facadeDAL);
		verseBLO = new VerseBLO(facadeDAL);

	}

	public IBooksBLO getBooksBLO() {
		return booksBLO;
	}

	public void setBooksBLO(IBooksBLO booksBLO) {
		this.booksBLO = booksBLO;
	}

	public IPeomBLO getPeomBLO() {
		return peomBLO;
	}

	public void setPeomBLO(IPeomBLO peomBLO) {
		this.peomBLO = peomBLO;
	}

	public IRootsBLO getRootsBLO() {
		return rootsBLO;
	}

	public void setRootsBLO(IRootsBLO rootsBLO) {
		this.rootsBLO = rootsBLO;
	}

	public ITokenBLO getTokenBLO() {
		return tokenBLO;
	}

	public void setTokenBLO(ITokenBLO tokenBLO) {
		this.tokenBLO = tokenBLO;
	}

	public IVerseBLO getVerseBLO() {
		return verseBLO;
	}

	public void setVerseBLO(IVerseBLO verseBLO) {
		this.verseBLO = verseBLO;
	}

	
	
}
