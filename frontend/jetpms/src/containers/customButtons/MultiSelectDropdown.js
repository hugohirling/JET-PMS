import React, { useState } from 'react';
import { Form } from 'react-bootstrap';

const MultiSelectDropdown = (props) => {
  const [selectedOptions, setSelectedOptions] = useState([...Array(props.data.length).keys()]);
  const [isOpen, setIsOpen] = useState(false);

  const options = props.data

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const handleOptionChange = (event) => {
    const optionId = parseInt(event.target.value);
    const isChecked = event.target.checked;

    if (isChecked) {
      setSelectedOptions([...selectedOptions, optionId]);
    } else {
      setSelectedOptions(selectedOptions.filter((id) => id !== optionId));
    }
    props.onChange(optionId)
  };

  return (
    <div className={`dropdown ${isOpen ? 'show' : ''}`}>
      <button
        style={{width:"20%"}}
        className="btn btn-secondary dropdown-toggle"
        type="button"
        id="multiSelectDropdown"
        onClick={toggleDropdown}
      >
        Spalten ein-/ausschalten
      </button>
      <div style={{width:"20%"}} className={`dropdown-menu ${isOpen ? 'show' : ''}`} aria-labelledby="multiSelectDropdown">
        {options.map((option) => (
          <Form.Check
          style={{marginLeft:"10%"}}
            key={option.id}
            type="checkbox"
            id={`option_${option.id}`}
            label={option.value}
            checked={selectedOptions.includes(option.id)}
            onChange={handleOptionChange}
            value={option.id}
          />
        ))}
      </div>
    </div>
  );
};

export default MultiSelectDropdown;