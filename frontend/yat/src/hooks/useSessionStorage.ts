import { useState, useEffect } from "react";

const getSessionStorage = (key: string, defaultValue: any) => {
  const storedValue = sessionStorage.getItem(key);
  if (!storedValue) {
    return defaultValue;
  }
  return JSON.parse(storedValue);
}

export const useSessionStorage = (key: string, defaultValue: any) => {
  const [ value, setValue ] = useState(getSessionStorage(key, defaultValue));

  useEffect(() => {
    sessionStorage.setItem(key, JSON.stringify(value));
  }, [key, value]);

  return [value, setValue];
}