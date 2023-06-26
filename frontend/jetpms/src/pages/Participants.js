import PTable from "../containers/participants/PTable";
import 'bootstrap/js/dist/dropdown'
import React, {useState} from 'react';
import MultiSelectDropdown from "../containers/customButtons/MultiSelectDropdown";

function Participants() {

    const p = [{id: 1, first: "First", last: "Last"},{id: 2, first: "First1", last: "Last1"}]
    const [dropDownData, setDropDownData] = useState([
        {id:0, value: "ID"},
        {id:1, value: "Vorname"},
        {id:2, value: "Nachname"},
        {id:3, value: "OG"},
        {id:4, value: "Geburtsdatum"},
        {id:5, value: "Alter"},
        {id:6, value: "Telefonnummer"},
        {id:7, value: "Email"},
        {id:8, value: "Threema-ID"},
        {id:9, value: "Notfallnummer"},
        {id:10, value: "DRSA"},
        {id:11, value: "TShirt"},
        {id:12, value: "Hose"},
        {id:13, value: "Jacke"},
        {id:14, value: "Bildrechte"},
        {id:15, value: "letzte Veranstaltung"},
    ])

    const [dropDownCheck, setDropDownCheck] = useState(Array(16).fill(true))

    function changeCheckBoxValue(i) {
        const list = [...dropDownCheck];
        list[i] = !list[i]

        setDropDownCheck(list)
    }

    return(
        <div className="ms-5 me-5 pt-2">
            <MultiSelectDropdown data={dropDownData} onChange={(id) => changeCheckBoxValue(id)} className="pe-3"/>
            <PTable participants={p} checkList={dropDownCheck}/>
        </div>
    )
}
export default Participants;