
function PTable(props) {

    let tableBody
    if(props.participants != null) {
        tableBody = (
            <tbody>
                {Array.from({length: props.participants.length}, (_,i) =>
                {
                    let p = props.participants[i]
                    if(p != null) {
                        return(
                            <tr key={p.id}>
                                {props.checkList[0] ? <th scope="row">{p.id}</th> : <></> }
                                {props.checkList[1] ? <td>{p.first}</td> : <></> }
                                {props.checkList[2] ? <td>{p.last}</td> : <></> }
                                {props.checkList[3] ? <td>{p.og}</td> : <></> }
                                {props.checkList[4] ? <td>{p.birthdate}</td> : <></> }
                                {props.checkList[5] ? <td>{p.age}</td> : <></> }
                                {props.checkList[6] ? <td>{p.number}</td> : <></> }
                                {props.checkList[7] ? <td>{p.email}</td> : <></> }
                                {props.checkList[8] ? <td>{p.threema}</td> : <></> }
                                {props.checkList[9] ? <td>{p.emergNumber}</td> : <></> }
                                {props.checkList[10] ? <td>{p.drsa}</td> : <></> }
                                {props.checkList[11] ? <td>{p.tshirt}</td> : <></> }
                                {props.checkList[12] ? <td>{p.pants}</td> : <></> }
                                {props.checkList[13] ? <td>{p.jacket}</td> : <></> }
                                {props.checkList[14] ? <td>{p.imageRight}</td> : <></> }
                                {props.checkList[15] ? <td>{p.lastEvent}</td> : <></> }
                            </tr>
                        )
                    }else{
                        return(<></>)
                    }
                })}
            </tbody>
        );
    }

    return(
        <div className="rounded-3 text-bg-dark px-1 pt-1">
            <table className="table table-dark table-hover">
                <thead>
                    <tr>
                        {props.checkList[0] ? <th scope="col">ID</th> : <></> }
                        {props.checkList[1] ? <th scope="col">Vorname</th> : <></> }
                        {props.checkList[2] ? <th scope="col">Nachname</th> : <></> }
                        {props.checkList[3] ? <th scope="col">OG</th> : <></> }
                        {props.checkList[4] ? <th scope="col">Geburtsdatum</th> : <></> }
                        {props.checkList[5] ? <th scope="col">Alter</th> : <></> }
                        {props.checkList[6] ? <th scope="col">Telefonnummer</th> : <></> }
                        {props.checkList[7] ? <th scope="col">Email</th> : <></> }
                        {props.checkList[8] ? <th scope="col">Threema-ID</th> : <></> }
                        {props.checkList[9] ? <th scope="col">Notfallnummer</th> : <></> }
                        {props.checkList[10] ? <th scope="col">DRSA</th> : <></> }
                        {props.checkList[11] ? <th scope="col">TShirt</th> : <></> }
                        {props.checkList[12] ? <th scope="col">Hose</th> : <></> }
                        {props.checkList[13] ? <th scope="col">Jacke</th> : <></> }
                        {props.checkList[14] ? <th scope="col">Bilderrecht</th> : <></> }
                        {props.checkList[15] ? <th scope="col">letzte Veranstaltung</th> : <></> }
                    </tr>
                </thead>
                {tableBody}
            </table>
        </div>
    );
}
export default PTable;