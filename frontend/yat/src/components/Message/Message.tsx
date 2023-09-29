export const Message = (
    {
      messageText
    }: {
      messageText: string
    }
) => {
  return (
    <div>
      <h1 className='card-title display-1 mb-5'>You're in a Tavern</h1>
      <h2 className='card-text display-2 mb-5'>
        {messageText}
      </h2>
    </div>
  )
}