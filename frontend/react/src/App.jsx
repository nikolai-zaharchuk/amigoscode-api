import {use, useEffect, useState} from "react";
import UserProfile from "./UserProfile.jsx";

import SidebarWithHeader from "./shared/SideBar.jsx";
import {getCustomers} from "./services/client.js";





function App() {

  useEffect(() => {
    getCustomers().then(response => {
      console.log(response)
    }).catch(err => {
      console.log(err)
    })
  }, []);


  return (
    <SidebarWithHeader/>
  );
}

export default App
