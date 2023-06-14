import React, { useState, useEffect } from 'react';

const data = [{id: 0, label: "Menace VS Random"}, {id: 1, label: "Menace VS Human"}];

const Dropdown = (props) => {
  const [isOpen, setOpen] = useState(false);
  const [items, setItem] = useState(data);
  const [selectedItem, setSelectedItem] = useState(null);
  
  const toggleDropdown = () => setOpen(!isOpen);
  
  const handleItemClick = (id) => {
    toggleDropdown();
    selectedItem == id ? setSelectedItem(null) : setSelectedItem(id);
    props.getCurrentGame(id);
  }
  
  return (
    <div className='dropdown'>
      <div className='dropdown-header' onClick={toggleDropdown}>
        {selectedItem ? items.find(item => item.id == selectedItem).label : items[0].label}
      </div>
      <div className={`dropdown-body ${isOpen && 'open'}`}>
        {items.map((item, idx) => (
          <div key={idx} className="dropdown-item" onClick={e => handleItemClick(e.target.id)} id={item.id}>
            <span className={`dropdown-item-dot ${item.id == selectedItem && 'selected'}`}>• </span>
            {item.label}
          </div>
        ))}
      </div>
    </div>
  )
}

export default Dropdown;