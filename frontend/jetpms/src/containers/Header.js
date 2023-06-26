import { House, Bucket, CalendarEvent, PeopleFill } from 'react-bootstrap-icons';
import {Link} from "react-router-dom";

function Header() {
    return(
        <header>
            <div className="px-3 py-2 text-bg-dark border-bottom">
                <div className="container">
                    <div className="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                        <Link className="d-flex align-items-center my-2 my-lg-0 me-lg-auto text-white text-decoration-none" to="/">
                            <img src="logo.svg" className="bi me-2" height="80" role="svg"/>
                        </Link>

                        <ul className="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
                            <li>
                                <Link className="nav-link text-secondary" to="/">
                                    <svg className="bi d-block mx-auto mb-1" width="24" height="24"><House  size={24}/></svg>
                                    Home
                                </Link>
                            </li>
                            <li>
                                <Link className="nav-link text-white" to="/participants">
                                    <svg className="bi d-block mx-auto mb-1" width="24" height="24"><PeopleFill  size={24}/></svg>
                                    Teilnehmer
                                </Link>
                            </li>
                            <li>
                                <Link className="nav-link text-white" to="/equipment">
                                    <svg className="bi d-block mx-auto mb-1" width="24" height="24"><Bucket  size={24}/></svg>
                                    Ausrüstung
                                </Link>
                            </li>
                            <li>
                                <Link className="nav-link text-white" to="/events">
                                    <svg className="bi d-block mx-auto mb-1" width="24" height="24"><CalendarEvent  size={24}/></svg>
                                    Veranstaltungen
                                </Link>
                            </li>
                        </ul>
                        <div className="text-end">
                            <button type="button" className="btn btn-light text-dark me-2">Login</button>
                        </div>
                    </div>
                    
                </div>
            </div>
        </header>
    );
}

export default Header;